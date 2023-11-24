package com.cibertec.apprestaurante.Pedidos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Model.Pedidos

class PedidosAdapter(val mItemClickListener: ItemClickListener):
    RecyclerView.Adapter<PedidosViewHolder>()
{
    private var pedidosList = emptyList<PedidosFirebase>()


    fun setPedisos(pedidos:List<PedidosFirebase>){
        this.pedidosList=pedidos
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            PedidosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PedidosViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return pedidosList.size
    }

    override fun onBindViewHolder(holder: PedidosViewHolder, position: Int) {
        val pedidos: PedidosFirebase = pedidosList[position]
        holder.bind(pedidos)
        holder.itemView.setOnClickListener{
            mItemClickListener.onItemClick(pedidos)
        }
    }


    interface ItemClickListener{
        fun onItemClick(pedidos: PedidosFirebase)
    }
}