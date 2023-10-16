package com.cibertec.apprestaurante.Cocina

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.database.Mesa
import com.cibertec.apprestaurante.R

class CocinaViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(
        R.layout.item_cocina, parent, false))
{

    private var textNumero: TextView? = null
    private var textNombre: TextView? = null

    init {
        textNumero = itemView.findViewById(R.id.numeromesaCocina)
    }


    fun bind(mesa: Mesa) {
        textNumero?.text = mesa.numero.toString()
    }
}