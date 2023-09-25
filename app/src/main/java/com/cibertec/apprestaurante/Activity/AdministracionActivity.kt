package com.cibertec.apprestaurante.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.apprestaurante.R

class AdministracionActivity: AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrar)
/*
        val btnCategory = findViewById<Button>(R.id.btnCategoria)
        btnCategory.setOnClickListener {
            startActivity(Intent(this, PedidosActivity:: class.java))
        }

        val btnPlatos = findViewById<Button>(R.id.btnPlatos)
        btnPlatos.setOnClickListener {
            startActivity(Intent(this, CocinaActivity::class.java))
        }*/
    }

}