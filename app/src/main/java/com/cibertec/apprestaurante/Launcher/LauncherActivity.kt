package com.cibertec.apprestaurante.Launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cibertec.apprestaurante.Activity.RolActivity
import com.cibertec.apprestaurante.R
import com.cibertec.resprueba.login.LoginActivity

class LauncherActivity: AppCompatActivity() {
    private lateinit var viewModel: LauncherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        viewModel = ViewModelProvider(this)[LauncherViewModel::class.java]
        viewModel.verifySession()

        observerViewModel()

    }

    private fun observerViewModel() {
        viewModel.userSessionService.observe(this) {
            if (it) {
                startActivity(Intent(this, RolActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }
}