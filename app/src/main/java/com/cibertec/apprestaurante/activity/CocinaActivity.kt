package com.cibertec.apprestaurante.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.cibertec.apprestaurante.Cocina.CocinaAdapter
import com.cibertec.apprestaurante.Mesa.MesaAdapter
import com.cibertec.apprestaurante.Mesa.MesaViewModel
import com.cibertec.apprestaurante.database.Mesa
import com.cibertec.apprestaurante.R


class CocinaActivity : AppCompatActivity() , CocinaAdapter.ItemClickListener
{
    private lateinit var mesaViewModel: MesaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocina)

        mesaViewModel=run {
            ViewModelProvider(this)[MesaViewModel::class.java]
        }
       val recyclerMesas = findViewById<RecyclerView>(R.id.recyclerMesasCocina)
        val adapter= CocinaAdapter(this)
        recyclerMesas.adapter=adapter
        recyclerMesas.layoutManager=LinearLayoutManager(this)

        mesaViewModel.mesas?.observe(this){ mesas->
            mesas?.let{
                adapter.setMesa(mesas)
            }
        }


            //layoutManager = GridLayoutManager(context, 2)
            // layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
          //  adapter = CocinaAdapter(listMesa)
        }

    override fun onItemClick(mesa: Mesa) {

    }
}


