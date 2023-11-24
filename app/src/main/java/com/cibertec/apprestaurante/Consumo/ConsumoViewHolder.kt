package com.cibertec.apprestaurante.Consumo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Mesa.MesaFirebase
import com.cibertec.apprestaurante.Model.Foods
import com.cibertec.apprestaurante.Productos.ProductosFirebase
import com.cibertec.apprestaurante.R

class ConsumoViewHolder (inflater: LayoutInflater, parent: ViewGroup):
RecyclerView.ViewHolder(inflater.inflate(
    R.layout.item_foods, parent, false)){

    private var imgFood: ImageView? = null
    private var textTitle: TextView? = null
    private var textPrice: TextView? = null
    private var textDescription: TextView? = null

    init {
        imgFood = itemView.findViewById(R.id.imgPort)
        textTitle = itemView.findViewById(R.id.textTit)
        textPrice = itemView.findViewById(R.id.textPri)
        textDescription = itemView.findViewById(R.id.textDesc)
    }

    fun bind(prod: ProductosFirebase){
        val nombreProducto=prod.nombre
        val textoPrecio=prod.precio
    /*    val nombreProducto =  StringBuilder()
        val textoPrecio = StringBuilder()

        for (producto in prod.consumo) {
            nombreProducto.append(producto.nombre).append("\n") // Suponiendo que 'nombre' es el campo del nombre del producto
            textoPrecio.append(producto.precio).append("\n") // Suponiendo que 'precio' es el campo del precio del producto
        }
*/

        textTitle?.text = nombreProducto
        textPrice?.text =textoPrecio
    }
}