package com.cibertec.apprestaurante.Registro

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroViewModel: ViewModel() {
    private val authFirebase = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    val userRegisterService = MutableLiveData<Boolean>()

    fun register(nombre: String, apellidos: String, email: String, pass: String, tipo:String) {
        registerFirebase(nombre, apellidos, email, pass, tipo)
    }

    private fun registerFirebase(nombre: String, apellidos: String, email: String, pass: String, tipo: String) {
        authFirebase.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful) {
                    val uid = task.result?.user?.uid
                    if (uid != null) {
                        registerFirestore(nombre, apellidos, email, pass, tipo, uid)
                    }
                } else {
                    userRegisterService.value = false
                }
            }
    }
    private fun registerFirestore(nombre: String, apellidos: String, correo: String, pass: String, tipo: String, uid: String) {
        val nuevoUsuario = UsuarioFirestore(nombre, apellidos, correo, pass, tipo)
        firestore.collection("usuario").document(uid).set(nuevoUsuario)
            .addOnCompleteListener(Activity()) { task ->
                userRegisterService.value = task.isSuccessful
            }
    }
}
