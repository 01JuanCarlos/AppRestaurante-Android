package com.cibertec.apprestaurante.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Model.Pedidos
import com.cibertec.apprestaurante.viewholder.PedidosViewHolder

class PedidosAdapter(val list: List<Pedidos>):
    RecyclerView.Adapter<PedidosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            PedidosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PedidosViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PedidosViewHolder, position: Int) {
        val pedidos: Pedidos = list[position]
        holder.bind(pedidos)
    }


}