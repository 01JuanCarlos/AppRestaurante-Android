package com.cibertec.apprestaurante.Categoria

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.database.Categoria
import java.util.zip.Inflater

class CategoriaViewHolder (inflater: LayoutInflater, parent: ViewGroup):
RecyclerView.ViewHolder(inflater.inflate(R.layout.item_categorias, parent, false)){

    private var txtCategoria: TextView? = null

    init {
        txtCategoria = itemView.findViewById(R.id.txtCategoria)
    }

    fun bind(categoria: Categoria){
        txtCategoria?.text = categoria.nombre
    }
}