package com.cibertec.apprestaurante

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.apprestaurante.activity.CocinaActivity
import com.cibertec.apprestaurante.activity.PedidosActivity

class AdministradorActivity: AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.administrador_activity)

        val btnRegistrer = findViewById<Button>(R.id.btnCategoria)
        btnRegistrer.setOnClickListener {
            startActivity(Intent(this, PedidosActivity:: class.java))
        }

        val btnIngresar = findViewById<Button>(R.id.btnPlatos)
        btnIngresar.setOnClickListener {
            startActivity(Intent(this, CocinaActivity::class.java))
        }
    }

}