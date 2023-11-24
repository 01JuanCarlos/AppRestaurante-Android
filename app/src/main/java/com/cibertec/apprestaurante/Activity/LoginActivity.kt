package com.cibertec.apprestaurante.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.Registro.RegistroActivity
import com.cibertec.apprestaurante.ViewHolder.LoginViewModel
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity(){

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.verifySession()

        val edtEmail = findViewById<TextInputEditText>(R.id.edtCorreo)
        val edtPassword = findViewById<TextInputEditText>(R.id.edtContraseña)


        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        btnIngresar.setOnClickListener {
            val email = edtEmail.text.toString()
            val pass = edtPassword.text.toString()
            viewModel.login(email, pass)

        }


        observerViewModel()


        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener{
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }



    private fun observerViewModel() {
        viewModel.userSessionService.observe(this){
            if(it){
                startActivity(Intent(this,RolActivity::class.java))
            }
        }
        viewModel.userLoginService.observe(this) {
            if (it) {
                startActivity(Intent(this, RolActivity::class.java))
            } else {
                Toast.makeText(this, "Verifica tus credenciales",
                    Toast.LENGTH_SHORT).show()
            }
        }


    }
}