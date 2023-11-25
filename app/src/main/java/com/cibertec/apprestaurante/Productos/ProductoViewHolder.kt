package com.cibertec.apprestaurante.Productos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.database.Plato

class ProductoViewHolder (inflater: LayoutInflater, parent: ViewGroup):
RecyclerView.ViewHolder(inflater.inflate(R.layout.item_foods, parent, false)){

    private var textTitle: TextView? = null
    private var textPrice: TextView? = null
    private var textDescription: TextView? = null

    init {
        textTitle = itemView.findViewById(R.id.textTit)
        textPrice = itemView.findViewById(R.id.textPri)
        textDescription = itemView.findViewById(R.id.textDesc)
    }

    fun bind(producto: ProductosFirebase){
        textTitle?.text = producto.nombre
        textPrice?.text = producto.precio.toString()
       // textDescription?.text = producto.descripcion
    }
}