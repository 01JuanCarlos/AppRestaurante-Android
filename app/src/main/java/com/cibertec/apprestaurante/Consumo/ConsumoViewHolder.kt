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
        imgFood = itemView.findViewById(R.id.imgPort)
        textTitle = itemView.findViewById(R.id.textTituloPlato)
        textDescription = itemView.findViewById(R.id.textDesc)
        textEspecificacion = itemView.findViewById(R.id.text_especif)
        textPrice=itemView.findViewById(R.id.txt_precio)

    }

    fun bind(prod: ProductosFirebase){
        val nombreProducto=prod.nombre
        val textoPrecio=prod.precio
        val especificacion=prod.especificacion
        val imagen=prod.imagen
        val cantidad=prod.cantidad
        val fecha=prod.fecha
    /*    val nombreProducto =  StringBuilder()
        val textoPrecio = StringBuilder()

        for (producto in prod.consumo) {
            nombreProducto.append(producto.nombre).append("\n") // Suponiendo que 'nombre' es el campo del nombre del producto
            textoPrecio.append(producto.precio).append("\n") // Suponiendo que 'precio' es el campo del precio del producto
        }
*/
        textCantidas?.text=cantidad.toString()
        textEspecificacion?.text=especificacion
        textTitle?.text = nombreProducto
        textPrice?.text = textoPrecio.toString()
        textFecha?.text=fecha
    }
}