package com.cibertec.apprestaurante.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.adapter.CocinaAdapter
import com.cibertec.apprestaurante.models.Cocina

class CocinaActivity : AppCompatActivity()
{
    private val listCocina = listOf(
        Cocina("MESA \n","1"),
        Cocina("MESA \n", "2"),
        Cocina("MESA \n", "3"),
        Cocina("MESA \n", "4")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vistas_cocina_activity)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerMesas)
        recyclerView.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CocinaAdapter(listCocina)
        }
    }

}
