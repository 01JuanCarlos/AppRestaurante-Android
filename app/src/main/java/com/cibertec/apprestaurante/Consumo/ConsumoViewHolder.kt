package com.cibertec.apprestaurante.Consumo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.cibertec.apprestaurante.Productos.ProductosFirebase
import com.cibertec.apprestaurante.R

class ConsumoViewHolder (inflater: LayoutInflater, parent: ViewGroup):
RecyclerView.ViewHolder(inflater.inflate(
    R.layout.item_consumo, parent, false)){

    private var imgFood: ImageView? = null
    private var textTitle: TextView? = null
    private var textCantidas: TextView? = null
    private var textFecha: TextView? = null

    private var textPrice: TextView? = null
    private var textDescription: TextView? = null
    private var textEspecificacion: TextView? = null



    init {
        textFecha=itemView.findViewById(R.id.text_fecha)
        textCantidas=itemView.findViewById(R.id.textCnatidadPlatos)
        imgFood = itemView.findViewById(R.id.imagenPlato)
        textTitle = itemView.findViewById(R.id.textTituloPlato)
        textDescription = itemView.findViewById(R.id.textDesc)
        textEspecificacion = itemView.findViewById(R.id.text_especif)
        textPrice=itemView.findViewById(R.id.txt_precio)

    }

    fun bind(prod: ProductosFirebase){
        val nombreProducto=prod.nombre
        val textoPrecio=prod.precio
        val especificacion=prod.especificacion
        val cantidad=prod.cantidad
        val fecha=prod.fecha


        textCantidas?.text=cantidad.toString()
        textEspecificacion?.text=especificacion
        textTitle?.text = nombreProducto
        textPrice?.text = textoPrecio.toString()
        textFecha?.text=fecha


        val options = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        imgFood?.let {
            Glide.with(it)
                .setDefaultRequestOptions(options)
                .load(prod.imagen)
                .into(it)
        }
    }
}