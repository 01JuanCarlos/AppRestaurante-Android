package com.cibertec.apprestaurante.Mesa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.R

class MesaAdapter(val mItemClickListener: ItemClickMesa):
    RecyclerView.Adapter<MesaViewHolder>(){

    private var mesaList = emptyList<MesaFirebase>()

    fun setMesa(mesa: List<MesaFirebase>) {
        this.mesaList = mesa
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MesaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MesaViewHolder(inflater, parent)    }

    override fun getItemCount(): Int {
        return mesaList.size
    }

    override fun onBindViewHolder(holder: MesaViewHolder, position: Int) {
        val mesa:MesaFirebase = mesaList[position]
        holder.bind(mesa)

        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(mesa)
        }
    }
    // Evento
    interface ItemClickMesa {
        fun onItemClick(mesa: MesaFirebase)
    }
}