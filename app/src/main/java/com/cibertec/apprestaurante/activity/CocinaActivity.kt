package com.cibertec.apprestaurante.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.cibertec.apprestaurante.Adapter.CocinaAdapter
import com.cibertec.apprestaurante.Model.Mesa
import com.cibertec.apprestaurante.R


class CocinaActivity : AppCompatActivity()
{
    private val listMesa = listOf(

    Mesa(1,"Juan Miranda"),
    Mesa(2,"Tony Estrella"),
    Mesa(3,"Andres"),
    Mesa(4,"Pepe"),
    Mesa(5,"Miguel"),
    Mesa(6,"Raul"),
    Mesa(7,"Pepe"),
    Mesa(8,"Miguel"),
    Mesa(9,"Raul"),
    Mesa(10,"Pepe"),
    Mesa(11,"Miguel"),
    Mesa(12,"Raul"),

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocina)

       val recyclerNews = findViewById<RecyclerView>(R.id.recyclerMesas)
        recyclerNews.apply {
             layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            //layoutManager = GridLayoutManager(context, 2)
            // layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = CocinaAdapter(listMesa)
        }
    }

}
