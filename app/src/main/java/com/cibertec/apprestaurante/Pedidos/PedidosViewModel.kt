package com.cibertec.apprestaurante.Pedidos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class PedidosViewModel: ViewModel() {
    private lateinit var firestore: FirebaseFirestore

    val listPedidoMutable = MutableLiveData<List<PedidosFirebase>>()

    fun getPlatoFirebase() {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("pedidos").get()
            .addOnSuccessListener { documentList ->
                var listaPedidos = arrayListOf<PedidosFirebase>()
                for (document in documentList) {
                    val nombre = document.getString("nombre")
                    val categoria = document.getString("categoria")
                    val imagen = document.getString("imagen")

                    if (nombre != null && categoria != null && imagen != null) {
                        val pedidos =
                            PedidosFirebase(nombre, categoria, imagen)
                        listaPedidos.add(pedidos)
                    }
                }
                listPedidoMutable.value = listaPedidos
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }
    }

}