package com.cibertec.apprestaurante.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.database.Mesa
import com.cibertec.apprestaurante.viewholder.CocinaViewHolder

class CocinaAdapter(val list: List<Mesa>):
    RecyclerView.Adapter<CocinaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocinaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CocinaViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: CocinaViewHolder, position: Int) {
        val mesa: Mesa = list[position]
        holder.bind(mesa)
    }

}