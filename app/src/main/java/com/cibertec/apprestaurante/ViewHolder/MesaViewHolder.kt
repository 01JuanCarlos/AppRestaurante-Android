package com.cibertec.apprestaurante.ViewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Model.Mesa
import com.cibertec.apprestaurante.R
import java.util.zip.Inflater

class MesaViewHolder(inflater: LayoutInflater, parent: ViewGroup):RecyclerView.ViewHolder(inflater.inflate(
    R.layout.items_news, parent, false)){


    private var textNumero: TextView? = null
    private var textNombre: TextView? = null

    init {
        textNumero = itemView.findViewById(R.id.textnumero)
        textNombre = itemView.findViewById(R.id.textnombre)
    }

    fun bind(mesa: Mesa) {
        textNumero?.text = mesa.numero.toString()
        textNombre?.text=mesa.nombre
    }

}