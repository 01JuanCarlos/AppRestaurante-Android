package com.cibertec.apprestaurante.Consumo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Mesa.MesaFirebase
import com.cibertec.apprestaurante.Mesa.MesaViewHolder
import com.cibertec.apprestaurante.Productos.ProductosFirebase

class ConsumoAdapter(val mItemClickListener: ItemClickConsumo):
    RecyclerView.Adapter<ConsumoViewHolder>(){

    private var consumoList = emptyList<ProductosFirebase>()

    fun setConsumo(consumo: List<ProductosFirebase>) {
        this.consumoList = consumo
        this.notifyDataSetChanged()
    }


    // Evento
    interface ItemClickConsumo {
        fun onItemClick(consumo: ProductosFirebase)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsumoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ConsumoViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return consumoList.size
    }

    override fun onBindViewHolder(holder: ConsumoViewHolder, position: Int) {
        val prod:ProductosFirebase = consumoList[position]
        holder.bind(prod)

        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(prod)
        }
    }
}