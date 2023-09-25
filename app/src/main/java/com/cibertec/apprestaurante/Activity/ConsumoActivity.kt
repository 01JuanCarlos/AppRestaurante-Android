package com.cibertec.apprestaurante.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

import com.cibertec.apprestaurante.R

class ConsumoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumo)

        val btn_add = findViewById<ImageButton>(R.id.btn_add)
        btn_add.setOnClickListener {
            startActivity(Intent(this, CategoriasActivity:: class.java))
        }

    }
}