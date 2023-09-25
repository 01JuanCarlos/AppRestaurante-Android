package com.cibertec.apprestaurante.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Model.Mesa
import com.cibertec.apprestaurante.viewholder.ViewHolderCocina

class CocinaAdapter(val list: List<Mesa>):
    RecyclerView.Adapter<ViewHolderCocina>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCocina {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolderCocina(inflater, parent)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: ViewHolderCocina, position: Int) {
        val mesa: Mesa = list[position]
        holder.bind(mesa)
    }

}