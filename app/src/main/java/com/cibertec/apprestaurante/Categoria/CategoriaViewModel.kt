package com.cibertec.apprestaurante.Categoria

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cibertec.apprestaurante.Mesa.MesaFirebase
import com.cibertec.apprestaurante.Productos.ProductosFirebase
import com.cibertec.apprestaurante.database.Categoria
import com.cibertec.apprestaurante.database.Mesa
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class CategoriaViewModel: ViewModel() {
    private lateinit var firestore: FirebaseFirestore

    val listCategoriasMutable = MutableLiveData<List<CategoriaFirestore>>()
    val listProductosMutable = MutableLiveData<List<ProductosFirebase>>()


    fun  getCategoriasFirebase(){
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("categoria").get()

            .addOnSuccessListener { documents ->

                var listCategoria = arrayListOf<CategoriaFirestore>()
                for (document in documents){
                    val id = document.id
                    val nombre = document.getString("nombre")


                    if (nombre != null){
                        val categorias = CategoriaFirestore(id,nombre)
                        listCategoria.add(categorias)
                    }
                }
                listCategoriasMutable.value = listCategoria

            }
            .addOnFailureListener{exception ->
                Log.d("TAG", "Error getting documents: ", exception)}
    }

    fun  getCategoriasID(id:String){
        firestore = FirebaseFirestore.getInstance()

        firestore.collection("categoria").whereEqualTo(FieldPath.documentId(), id)
            .get()
            .addOnSuccessListener { documentList ->
                val listCategoria = arrayListOf<CategoriaFirestore>()
                var nombre:String
                for (document in documentList) {
                    val campoProductosArray = document.get("productos") as? ArrayList<*>

                    val id = document.id
                     nombre=document.getString("nombre").toString()
                    val listProduct = arrayListOf<ProductosFirebase>()

                    campoProductosArray?.forEach { item ->
                        if (item is Map<*, *>) {
                            val nombreProducto = item["nombre_produc"] as? String
                            val precio = item["precio"] as? String
                            val descripcion = item["descripcion"] as String
                            val imagen = item["imagen"] as String

                            val precioDouble: Double? = precio?.toDoubleOrNull()
                            if (nombreProducto !=null && precio !=null && descripcion !=null && imagen !=null) {
                                val product = precioDouble?.let {
                                    ProductosFirebase(nombreProducto,precioDouble,descripcion,imagen)
                                }
                                if (product != null) {
                                    listProduct.add(product)

                                }

                            }
                        }
                    }

                    if (id != null && listProduct != null) {
                        val categoria = CategoriaFirestore(id,nombre,listProduct.toList())
                        listCategoria.add(categoria)
                    }
                }


                if (listCategoria != null) {
                    for (categoria in listCategoria) {

                        val ProductosCate = categoria.productos
                        val listpro = arrayListOf<ProductosFirebase>()

                        if (ProductosCate != null) {
                            for (producto in ProductosCate) {
                                val nombre=producto.nombre
                                val precio=producto.precio
                                val imagen=producto.imagen
                                val descrip=producto.descrip
                                val produc= ProductosFirebase(nombre,precio,descrip,imagen)
                                listpro.add(produc)
                            }
                            listProductosMutable.value=listpro
                            println("IMAGEN: "+listProductosMutable.value)

                        }
                    }}
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }


    }

    fun crearCategoria(categoria:String){
        val db = FirebaseFirestore.getInstance()
        val categoria = hashMapOf(
            "nombre" to categoria
        )

        db.collection("categoria")
            .add(categoria)
            .addOnSuccessListener { documentReference ->
            }
            .addOnFailureListener { e ->
            }

    }


}