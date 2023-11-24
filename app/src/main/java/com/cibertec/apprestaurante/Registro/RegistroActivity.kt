package com.cibertec.apprestaurante.Registro



import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cibertec.apprestaurante.R
import com.google.android.material.textfield.TextInputEditText

class RegistroActivity: AppCompatActivity() {


    private lateinit var viewModel: RegistroViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        viewModel = ViewModelProvider(this)[RegistroViewModel::class.java]

        val edtNombre =  findViewById<TextInputEditText>(R.id.edtNombres)
        val edtApellidos =  findViewById<TextInputEditText>(R.id.edtApellidos)
        val edtEmail = findViewById<TextInputEditText>(R.id.edtEmail)
        val edtPass = findViewById<TextInputEditText>(R.id.edtPass)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val nombre = edtNombre.text.toString()
            val apellidos = edtApellidos.text.toString()
            val email = edtEmail.text.toString()
            val pass = edtPass.text.toString()
            viewModel.register(nombre, apellidos, email, pass)
        }
        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.userRegisterService.observe(this) {
            if (it) {
                finish()
                // startActivity(Intent(this, WelcomeActivity::class.java))
            } else {
                Toast.makeText(this, "Verifica tus credenciales",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

}