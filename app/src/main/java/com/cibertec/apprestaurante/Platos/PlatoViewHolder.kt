package com.cibertec.apprestaurante.Platos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Model.Foods
import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.database.Plato

class PlatoViewHolder (inflater: LayoutInflater, parent: ViewGroup):
RecyclerView.ViewHolder(inflater.inflate(R.layout.item_foods, parent, false)){
    private var textTitle: TextView? = null
    private var textPrice: TextView? = null
    private var textDescription: TextView? = null

    init {
        textTitle = itemView.findViewById(R.id.textTit)
        textPrice = itemView.findViewById(R.id.textPri)
        textDescription = itemView.findViewById(R.id.textDesc)
    }

    fun bind(plato: Plato){
        textTitle?.text = plato.nombre
        textPrice?.text = plato.precio.toString()
        textDescription?.text = plato.descripcion
    }
}