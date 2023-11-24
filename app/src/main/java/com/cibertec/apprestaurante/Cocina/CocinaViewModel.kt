package com.cibertec.apprestaurante.Cocina

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cibertec.apprestaurante.Mesa.MesaFirebase
import com.google.firebase.firestore.FirebaseFirestore

class CocinaViewModel:ViewModel() {

    private lateinit var firestore: FirebaseFirestore

    val listMesasMutable = MutableLiveData<List<MesaFirebase>>()

    fun getMesaFirebase() {
   /*     firestore = FirebaseFirestore.getInstance()
        firestore.collection("mesas").get()
            .addOnSuccessListener { documentList ->
                var listaPlatos = arrayListOf<MesaFirebase>()
                for (document in documentList) {
                    val numero = document.getLong("Numero").toString()?.toInt()
                    val nombre = document.getString("Nombre")

                    if (nombre != null && numero != null ) {
                        val plato =
                            MesaFirebase(nombre,numero)
                        listaPlatos.add(plato)
                    }
                }
                listMesasMutable.value = listaPlatos
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }*/
    }
}