package com.cibertec.apprestaurante.Registro

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroViewModel: ViewModel() {

    private lateinit var authFirebase: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    val userRegisterService = MutableLiveData<Boolean>()

    fun register(nombre: String, apellidos: String,
                 email: String, pass: String) {
        registerFirebase(nombre, apellidos, email, pass)
    }

    private fun registerFirebase(nombre: String, apellidos: String,
                                 email: String, pass: String) {
        authFirebase = FirebaseAuth.getInstance()
        authFirebase.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful) {
                    val uid = task.result?.user?.uid
                    if (uid != null) {
                        registerFirestore(nombre, apellidos,
                            email, uid)
                    }
                } else {
                    userRegisterService.value = false
                }
            }
    }

    private fun registerFirestore(nombre: String, apellidos: String, correo: String,
                                  uid: String) {
        val usuario = UsuarioFirestore(nombre, apellidos, correo)
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("usuario").document(uid).set(usuario)
            .addOnCompleteListener(Activity()) { task ->
                userRegisterService.value = task.isSuccessful
            }
    }

}

data class UsuarioFirestore (
    var nombre: String,
    var apellidos: String,
    var correo: String
)