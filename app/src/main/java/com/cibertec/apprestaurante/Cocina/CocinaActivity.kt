package com.cibertec.apprestaurante.Cocina

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Mesa.MesaFirebase

import com.cibertec.apprestaurante.Mesa.MesaViewModel
import com.cibertec.apprestaurante.database.Mesa
import com.cibertec.apprestaurante.R


class CocinaActivity : AppCompatActivity() , CocinaAdapter.ItemClickListener
{
    private lateinit var ViewModel: CocinaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocina)

        ViewModel = ViewModelProvider(this)[CocinaViewModel::class.java]
        ViewModel.getMesaFirebase()


        ViewModel=run {
            ViewModelProvider(this)[CocinaViewModel::class.java]
        }
        val recyclerMesas = findViewById<RecyclerView>(R.id.recyclerMesasCocina)
        val adapter= CocinaAdapter(this)
        recyclerMesas.adapter=adapter
        recyclerMesas.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        ViewModel.listMesasMutable?.observe(this){ mesa->
            mesa?.let{
                adapter.setMesa(mesa)
            }

        }

        //layoutManager = GridLayoutManager(context, 2)
        // layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        //  adapter = CocinaAdapter(listMesa)
    }

    override fun onItemClick(mesa: MesaFirebase) {
        val intent = Intent(this, CocinaActivity::class.java)
        intent.putExtra("nombre", mesa.nombre)
        intent.putExtra("numero", mesa.numero)
        startActivity(intent)

    }
}
