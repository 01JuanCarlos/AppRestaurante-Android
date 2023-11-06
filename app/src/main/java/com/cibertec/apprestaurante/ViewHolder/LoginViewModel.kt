package com.cibertec.apprestaurante.ViewHolder

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {
    private lateinit var authFirebase: FirebaseAuth
     val userSessionService= MutableLiveData<Boolean>()
    val userLoginService = MutableLiveData<Boolean>()

    fun login(email: String, pass: String) {
        // loginService(email, pass)
        loginFirebase(email, pass)
    }
    private fun loginFirebase(email: String, pass: String) {
        authFirebase = FirebaseAuth.getInstance()
        authFirebase.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(Activity()) { task ->

                userLoginService.value = task.isSuccessful

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