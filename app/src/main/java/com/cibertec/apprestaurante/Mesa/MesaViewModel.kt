package com.cibertec.apprestaurante.Mesa

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cibertec.apprestaurante.Productos.ProductosFirebase
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore

class MesaViewModel: ViewModel() {

    private lateinit var firestore: FirebaseFirestore


    val listMesasMutable=MutableLiveData<List<MesaFirebase>>()
    val listConsumoMutable=MutableLiveData<List<ProductosFirebase>>()


    fun getMesasFirebase() {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("orden").get()
            .addOnSuccessListener { documentList ->
                val listMesas = arrayListOf<MesaFirebase>()

                for (document in documentList) {
                    val id = document.id
                    val fecha = document.getString("fecha").toString()
                    val nombre = document.getString("nombre")
                    val numero = document.getLong("mesa")?.toInt()
             //       val pagototal = document.getString("pago_total").toString()


                    if (id !=null && fecha !=null && nombre != null && numero != null) {
                        val mesa = MesaFirebase(id, nombre, numero,fecha)
                       // print("IDDD   $mesa")
                        listMesas.add(mesa)
                    }
                }
               // println("LIsta:MEsas:::::::  $listMesas")

                listMesasMutable.value = listMesas
/*
                if (listMesas != null) {
                    for (mesa in listMesas) {
                        println("ID Mesa: ${mesa.id}")
                        println("Nombre Mesa: ${mesa.nombre}")
                        // Otros datos de la mesa...

                        val consumoMesa = mesa.consumo // Suponiendo que 'consumo' es la lista de ProductosFirebase dentro de MesaFirebase

                        if (consumoMesa != null) {
                            for (producto in consumoMesa) {
                                println("Nombre Producto: ${producto.nombre}")
                                println("Precio Producto: ${producto.precio}")
                                // Otros datos del producto...
                            }
                        }
                    }}*/
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }
    }


    fun getConsumoID(id:String) {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("orden").whereEqualTo(FieldPath.documentId(), id) // Filtra por el ID recibido como parámetro
            .get()
            .addOnSuccessListener { documentList ->
                val listMesas = arrayListOf<MesaFirebase>()

                for (document in documentList) {
                    val consumoArray = document.get("consumo") as? ArrayList<*>

                    val id = document.id
                   //   val fecha = document.getString("fecha").toString()
                  //  val nombre = document.getString("nombre")
                  //  val numero = document.getLong("mesa")?.toInt()
                    //val pagototal = document.getString("pago_total").toString()

                    val listProduct = arrayListOf<ProductosFirebase>()

                    consumoArray?.forEach { item ->
                        if (item is Map<*, *>) {
                            val nombreProducto = item["nombre_produc"] as? String
                            val precio = item["precio"] as? String
                            val especifi=item["especif"] as String
                            val cantidad=item["cantidad"] as? Long
                            val fecha=item["fecha"] as String

                            if (nombreProducto !=null && precio !=null) {
                                val product = ProductosFirebase(nombreProducto,precio,cantidad.toString().toInt(),especifi,fecha)

                                listProduct.add(product)

                            }
                        }
                    }

                   if (id != null && listProduct != null) {
                        val mesa = MesaFirebase(id,listProduct.toList())
                        listMesas.add(mesa)
                    }
                }

                //listMesasMutable.value = listMesas

                if (listMesas != null) {
                    for (mesa in listMesas) {
               /*         println("ID Mesa: ${mesa.id}")
                        println("Nombre Mesa: ${mesa.nombre}")
*/
                        val consumoMesa = mesa.consumo
                        val listpro = arrayListOf<ProductosFirebase>()

                        if (consumoMesa != null) {
                            for (producto in consumoMesa) {
                                val nombre=producto.nombre
                                val precio=producto.precio
                                val esepecif=producto.especificacion
                                val imagen=producto.imagen
                                val cantidad=producto.cantidad
                                val fecha=producto.fecha

                                val produc=ProductosFirebase(nombre,precio,cantidad,esepecif,fecha)
                                println("Productos:   $produc")

                                listpro.add(produc)
/*
                                println("Nombre Producto: ${producto.nombre}")
                                println("Precio Producto: ${producto.precio}")
                                var i:Int=0
                                println("I: ${i++}")

*/
                                // Otros datos del producto...
                            }
                            listConsumoMutable.value=listpro
                        }
                    }}
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }
    }



}