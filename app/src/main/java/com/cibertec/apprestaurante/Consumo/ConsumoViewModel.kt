package com.cibertec.apprestaurante.Consumo


import androidx.lifecycle.ViewModel


class ConsumoViewModel: ViewModel() {

    /*private lateinit var firestore: FirebaseFirestore


    val listConsumoMutable=MutableLiveData<List<ProductosFirebase>>()


    fun getConsumoFirebase(){
        firestore=FirebaseFirestore.getInstance()
        firestore.collection("orden").get()
            .addOnSuccessListener { documentList ->
                var listProduct = arrayListOf<ProductosFirebase>()
                for (document in documentList) {
                    val consumoArray = document.get("consumo") as? ArrayList<*>
                    consumoArray?.forEach { item ->
                        if (item is Map<*, *>) {
                            val nombreProducto = item["nombre_produc"] as? String
                            val precio = item["precio"] as? String

                            if (nombreProducto != null && precio != null) {
                                val product = ProductosFirebase(nombreProducto, precio)
                                listProduct.add(product)

                            }

                        }
                    }
                }
                listConsumoMutable.value=listProduct
                println("netroooooo:"+listProduct.toString())
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }
    }*/

}