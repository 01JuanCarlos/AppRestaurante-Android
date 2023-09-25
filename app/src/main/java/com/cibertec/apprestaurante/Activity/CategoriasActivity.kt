package com.cibertec.apprestaurante.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.apprestaurante.R

class CategoriasActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_categorias)
        val btncate = findViewById<Button>(R.id.btncate)
        btncate.setOnClickListener{
            startActivity(Intent(this, FoodsActiviity::class.java))
        }
    }
}