package com.cibertec.apprestaurante.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.models.Cocina

class ViewHolderCocina(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(
        R.layout.cocina_activity, parent,
        false))
{

    private var textNumeroMesa: TextView? = null
    private var textMesa: TextView? = null

    init
    {
        textMesa = itemView.findViewById(R.id.textMesa)
        textNumeroMesa = itemView.findViewById(R.id.numeromesa)

    }

    fun bind(cocina: Cocina)
    {
        textNumeroMesa?.text = cocina.Title
        textMesa?.text = cocina.Cantidad

    }
}