package com.cibertec.apprestaurante.Cocina

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button

import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cibertec.apprestaurante.Mesa.MesaFirebase

import com.cibertec.apprestaurante.Mesa.MesaViewModel
import com.cibertec.apprestaurante.Pedidos.PedidosActivity
import com.cibertec.apprestaurante.database.Mesa
import com.cibertec.apprestaurante.R
import org.checkerframework.common.subtyping.qual.Bottom


class CocinaActivity : AppCompatActivity() , CocinaAdapter.ItemClickListener
{
    private lateinit var ViewModel: MesaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocina)

        ViewModel = ViewModelProvider(this)[MesaViewModel::class.java]
        ViewModel.getMesasFirebase()
        ViewModel=run {
            ViewModelProvider(this)[MesaViewModel::class.java]
        }
        val recyclerMesas = findViewById<RecyclerView>(R.id.recyclerMesasCocina)
        val adapter= CocinaAdapter(this)
        val espera=findViewById<Button>(R.id.btn_espera)
        val atendido=findViewById<Button>(R.id.btn_atend)
        val cancelado=findViewById<Button>(R.id.btn_cancel)

        recyclerMesas.adapter=adapter
        recyclerMesas.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        ViewModel.listMesasMutable?.observe(this){ mesa->
            mesa?.let{
                adapter.setMesa(mesa)
            }
        }
        espera.setOnClickListener{
            ViewModel.getEstado("En espera")
        }
        atendido.setOnClickListener{
            ViewModel.getEstado("Atendido")
        }
        cancelado.setOnClickListener{
            ViewModel.getEstado("Cancelado")
        }

        recyclerMesas.adapter=adapter
        recyclerMesas.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        ViewModel.listMesasMutable?.observe(this){ mesa->
            mesa?.let{
                adapter.setMesa(mesa)
            }
        }









       val swipe=findViewById<SwipeRefreshLayout>(R.id.swipe_Cocina)

        swipe.setColorSchemeResources(R.color.cheleste,R.color.green)
        swipe.setOnRefreshListener {
            val intent = intent
            Handler(Looper.getMainLooper()).postDelayed({
                finish()
                startActivity(intent)
                swipe.isRefreshing=false
            },1500)

        }




        //layoutManager = GridLayoutManager(context, 2)
        // layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        //  adapter = CocinaAdapter(listMesa)
    }

    override fun onItemClick(mesa: MesaFirebase) {
        val intent = Intent(this, PedidosActivity::class.java)
        intent.putExtra("nombre", mesa.nombre)
        intent.putExtra("id", mesa.id)
        println("ID: "+mesa.id)
        startActivity(intent)
    }
}
