package com.cibertec.apprestaurante.Activity

import android.content.Intent
import android.os.Bundle

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.apprestaurante.R

class BarActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_bar_menu)
        val btnme = findViewById<ImageView>(R.id.btn_menu)
        btnme.setOnClickListener{
            startActivity(Intent(this, AdministracionActivity::class.java))
        }
    }

}