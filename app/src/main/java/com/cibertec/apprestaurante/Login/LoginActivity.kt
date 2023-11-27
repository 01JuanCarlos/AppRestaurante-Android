package com.cibertec.resprueba.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cibertec.apprestaurante.Activity.AdministracionActivity
import com.cibertec.apprestaurante.Cocina.CocinaActivity
import com.cibertec.apprestaurante.Mesa.MesasActivity
import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.Registro.RegistroActivity

import com.google.android.material.textfield.TextInputEditText

class LoginActivity: AppCompatActivity() {
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

        viewModel.userSessionService.observe(this){ tieneSesion->
            if(tieneSesion){
                val tipoUsuario = viewModel.tipoUsuarioActual.value
                dirirgirSegunTipoUsuario(tipoUsuario)
            }
        }
        viewModel.userLoginService.observe(this) {inicioSesionExitoso ->
            if (inicioSesionExitoso ) {
                Toast.makeText(this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Verifica Credenciales", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun dirirgirSegunTipoUsuario(tipoUsuario: String?) {
        when (tipoUsuario){
            "Mesero" ->{
                startActivity(Intent(this, MesasActivity::class.java))
            }
            "Cocinero" ->{
                startActivity(Intent(this, CocinaActivity::class.java))
            }
            "Administrador" -> {
                startActivity(Intent(this, AdministracionActivity::class.java))
            }
            else ->{
                Toast.makeText(this, "Tipo de Usuario Desconocido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}