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
                    val pagototal = document.getString("pago_total").toString()


                    if (id !=null && fecha !=null && nombre != null && numero != null) {
                        val mesa = MesaFirebase(id, nombre, numero,fecha,pagototal)
                       // print("IDDD   $mesa")
                        listMesas.add(mesa)
                    }
                }
               // println("LIsta:MEsas:::::::  $listMesas")

                listMesasMutable.value = listMesas

            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }
    }


    fun getConsumoID(id:String) {
        firestore = FirebaseFirestore.getInstance()

            val docRef = firestore.collection("orden").document(id)
            docRef.get().addOnSuccessListener { documentSnapshot ->

                val listMesas = arrayListOf<MesaFirebase>()



                if (documentSnapshot.exists()) {


                    //obtenemos solo el consumo
                    val consumoArray =
                        documentSnapshot.get("consumo") as? List<Map<String, Any>> ?: listOf()


                    //recoger precios
                    val precios = consumoArray.mapNotNull { item ->
                        val precio = item["precio"] as? String
                        precio?.toDoubleOrNull() ?: 0.0
                    }
                    //recoger cantidades
                    val cantidades = consumoArray.mapNotNull { item ->
                        val cantidad = item["cantidad"] as? Long
                        cantidad?.toInt() ?: 0
                    }

                    //calcular monto total
                    val preciosConCantidades = precios.zip(cantidades)
                    val costoTotal =
                        preciosConCantidades.sumByDouble { (precio, cantidad) -> precio * cantidad }

                    //actulizamos el pago total
                    docRef.update("pago_total", costoTotal.toString())
                        .addOnSuccessListener {
                            // Éxito al actualizar el precio_total
                        }
                        .addOnFailureListener { e ->
                            // Error al actualizar el precio_total
                        }
                    val pagototal = documentSnapshot.getString("pago_total").toString()
                    val nombre = documentSnapshot.getString("nombre").toString()
                    val mesa = documentSnapshot.getLong("mesa")?.toInt() ?: 0

                    //Recorremos el consumo
                    val listProduct = arrayListOf<ProductosFirebase>()
                    consumoArray?.forEach { item ->
                        if (item is Map<*, *>) {
                            val nombreProducto = item["nombre_produc"] as? String
                            val precio = item["precio"] as? String
                            val especifi = item["especif"] as String
                            val cantidad = item["cantidad"] as? Long
                            val fecha = item["fecha"] as String

                            if (nombreProducto != null && precio != null) {
                                val product = ProductosFirebase(
                                    nombreProducto,
                                    precio.toDouble(),
                                    cantidad.toString().toInt(),
                                    especifi,
                                    fecha
                                )
                                listProduct.add(product)
                            }
                        }
                    }
                    //agregamos la lista de productos al objeto Mesa
                    if (id != null && listProduct != null) {
                        val mesa = MesaFirebase(id,nombre,mesa,listProduct,pagototal)
                        //lo agregamos a un array de mesas
                        listMesas.add(mesa)
                    }
                }

                //Recorremos el Aray de mesas
                if (listMesas != null) {

                    for (mesa in listMesas) {
                        //accedemos al campo consumo
                        val consumoMesa = mesa.consumo
                        val listpro = arrayListOf<ProductosFirebase>()
                        if (consumoMesa != null) {
                            //recorremos  el campo consumo
                            for (producto in consumoMesa) {
                                val nombre = producto.nombre
                                val precio = producto.precio
                                val esepecif = producto.especificacion
                                val imagen = producto.imagen
                                val cantidad = producto.cantidad
                                val fecha = producto.fecha
                                val produc =
                                    ProductosFirebase(nombre, precio, cantidad, esepecif, fecha)
                                listpro.add(produc)
                            }

                            //agregamos el consumo a una lista mutable
                            listConsumoMutable.value = listpro
                            listMesasMutable.value = listMesas
                            println("LISTA MESAA ${listMesasMutable.value}")


                        }
                    }
                }


            }
                .addOnFailureListener { exception ->
                    Log.d("TAG", "Error getting documents: ", exception)
                }


            }



}