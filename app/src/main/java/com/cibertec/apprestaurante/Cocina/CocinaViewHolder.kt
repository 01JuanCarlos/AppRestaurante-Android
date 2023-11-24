package com.cibertec.apprestaurante.Cocina

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.cibertec.apprestaurante.Mesa.MesaFirebase
import com.cibertec.apprestaurante.database.Mesa
import com.cibertec.apprestaurante.R

class CocinaViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(
        R.layout.item_cocina, parent, false))
{

    private var textMesa: TextView? = null
    private var numeromesaCocina: TextView? = null

    init {
        textMesa = itemView.findViewById(R.id.textMesa)
        numeromesaCocina = itemView.findViewById(R.id.numeromesaCocina)    }


    fun bind(mesa: MesaFirebase) {
        textMesa?.text = mesa.nombre
        numeromesaCocina?.text = mesa.numero.toString()

        val options = RequestOptions()
            .placeholder(R.drawable.mesas)
            .error(R.drawable.mesas)    }
}