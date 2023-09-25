package com.cibertec.apprestaurante.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.adapter.PedidosAdapter
import com.cibertec.apprestaurante.Model.Pedidos

class PedidosActivity: AppCompatActivity()
{
    private val listPedidos = listOf(
        Pedidos("JUGO DE LIMON\n", "Cantidad:  2\n",
            R.drawable.jugolimon
        ),
        Pedidos("ARROZ CON POLLO\n",
            "Cantidad:  1", R.drawable.arrozpollo
        ),
        Pedidos("ARROZ CHINO\n",
            "Cantidad:  1\n", R.drawable.arrozchino
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos_cocina)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerPedidos)
        recyclerView.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = PedidosAdapter(listPedidos)
        }
        }

    }
