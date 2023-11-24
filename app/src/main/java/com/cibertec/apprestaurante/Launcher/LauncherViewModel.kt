package com.cibertec.apprestaurante.Launcher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LauncherViewModel: ViewModel() {
    private lateinit var authFirebase: FirebaseAuth

    val userSessionService = MutableLiveData<Boolean>()

    fun verifySession() {
        val user = FirebaseAuth.getInstance().currentUser
        userSessionService.value = (user != null)
    }

}