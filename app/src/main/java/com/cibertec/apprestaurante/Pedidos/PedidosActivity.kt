package com.cibertec.apprestaurante.Pedidos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.Model.Pedidos

class PedidosActivity: AppCompatActivity(), PedidosAdapter.ItemClickListener
{
    private lateinit var viewModel: PedidosViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos_cocina)


        viewModel = ViewModelProvider(this)[PedidosViewModel::class.java]
        viewModel.getPlatoFirebase()


        val recyclePedido = findViewById<RecyclerView>(R.id.recyclerPedidos)
        val adapter= PedidosAdapter(this)
        recyclePedido.adapter=adapter
        recyclePedido.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        viewModel.listPedidoMutable?.observe(this){ pedidos->
            pedidos?.let{
                adapter.setPedisos(pedidos)
            }

        }
    }

    override fun onItemClick(pedidos: PedidosFirebase) {

        val intent = Intent(this, PedidosActivity::class.java)
        intent.putExtra("nombre", pedidos.nombre)
        intent.putExtra("categoria", pedidos.categoria)
        intent.putExtra("imagen", pedidos.imagen)
        startActivity(intent)

    }
}
