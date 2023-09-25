package com.cibertec.apprestaurante.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.apprestaurante.R

class AdministracionActivity: AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrar)

     val btnCategory = findViewById<Button>(R.id.btncate)
        btnCategory.setOnClickListener {
            startActivity(Intent(this,AddcategoriasActivity :: class.java))
        }

        val btnPlatos = findViewById<Button>(R.id.btn_add_platos)
        btnPlatos.setOnClickListener {
            startActivity(Intent(this, AddplatosActivity::class.java))
        }
    }

}