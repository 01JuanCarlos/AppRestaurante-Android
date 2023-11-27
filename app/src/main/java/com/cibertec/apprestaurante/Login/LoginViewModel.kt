package com.cibertec.resprueba.login

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginViewModel: ViewModel() {
    private lateinit var authFirebase: FirebaseAuth
    private val firestore = FirebaseFirestore.getInstance()

    val userSessionService = MutableLiveData<Boolean>()
    val userLoginService = MutableLiveData<Boolean>()
    val tipoUsuarioActual = MutableLiveData<String>()

    fun login(email: String, pass: String) {
        loginFirebase(email, pass)
    }
    private fun loginFirebase(email: String, pass: String) {
        authFirebase = FirebaseAuth.getInstance()
        authFirebase.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful){
                    obtenerTipoUsuario(authFirebase.currentUser?.uid.orEmpty())
                    userLoginService.value = true
                } else {
                    userLoginService.value = false
                }

            }
    }

    private fun obtenerTipoUsuario(uid: String){
        firestore.collection("usuario").document(uid).get()
            .addOnSuccessListener { documentSnapshot ->
                val tipoUsuario = documentSnapshot.getString("tipo")
                tipoUsuarioActual.value = tipoUsuario
                userSessionService.value = true
            }
            .addOnFailureListener{ e ->
                userLoginService.value = false
            }
    }

    fun verifySession(){
        val user = FirebaseAuth.getInstance().currentUser
        userSessionService.value=(user!=null)

    }
    fun cerrarSessio(){
        FirebaseAuth.getInstance().signOut()
    }
}