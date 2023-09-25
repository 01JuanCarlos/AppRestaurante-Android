package com.cibertec.apprestaurante.Activity


import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.cibertec.apprestaurante.R


class RolActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rol)

        val btnmenu = findViewById<ImageView>(R.id.btn_menu)
        btnmenu.setOnClickListener {
            startActivity(Intent(this, AdministracionActivity:: class.java))
        }
        val btnMesaero = findViewById<Button>(R.id.btnMesero)
        btnMesaero.setOnClickListener {
            startActivity(Intent(this, MesasActivity:: class.java))
        }

        val btnCocinero = findViewById<Button>(R.id.btncocinero)
        btnCocinero.setOnClickListener {
            startActivity(Intent(this, CocinaActivity::class.java))
        }
    }
}