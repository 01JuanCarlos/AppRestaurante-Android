package com.cibertec.apprestaurante

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.apprestaurante.activity.CocinaActivity
import com.cibertec.apprestaurante.activity.PedidosActivity

class seleccionRol: AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seleccion_rol_activity)

        val btnRegistrer = findViewById<Button>(R.id.btnMesero)
        btnRegistrer.setOnClickListener {
            startActivity(Intent(this, PedidosActivity:: class.java))
        }

        val btnIngresar = findViewById<Button>(R.id.btnCocinero)
        btnIngresar.setOnClickListener {
            startActivity(Intent(this, CocinaActivity::class.java))
        }
    }

}