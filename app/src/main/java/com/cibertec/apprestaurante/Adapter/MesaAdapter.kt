package com.cibertec.apprestaurante.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Model.Mesa
import com.cibertec.apprestaurante.ViewHolder.MesaViewHolder

class MesaAdapter(val list: List<Mesa>): RecyclerView.Adapter<MesaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MesaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MesaViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MesaViewHolder, position: Int) {
        val mesa: Mesa = list[position]
        holder.bind(mesa)
    }
}