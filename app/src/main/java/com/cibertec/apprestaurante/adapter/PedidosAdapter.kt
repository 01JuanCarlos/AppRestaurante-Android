package com.cibertec.apprestaurante.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.models.Pedidos
import com.cibertec.apprestaurante.viewholder.ViewHolderPedidos

class PedidosAdapter(val list: List<Pedidos>):
    RecyclerView.Adapter<ViewHolderPedidos>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolderPedidos {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolderPedidos(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderPedidos, position: Int) {
        val pedidos: Pedidos = list[position]
        holder.bind(pedidos)
    }


}