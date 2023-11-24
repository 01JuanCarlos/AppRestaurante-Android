package com.cibertec.apprestaurante.Pedidos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cibertec.apprestaurante.R

class PedidosViewHolder (inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(
        R.layout.item_consumo, parent,
        false))
{
    private var imagenPlato: ImageView? = null
    private var textTituloPlato: TextView? = null
    private var textCnatidadPlatos: TextView? = null

    init {
        imagenPlato = itemView.findViewById(R.id.imagenPlato)
        textTituloPlato = itemView.findViewById(R.id.textTituloPlato)
        textCnatidadPlatos = itemView.findViewById(R.id.textCnatidadPlatos)
    }


    fun bind(pedido: PedidosFirebase) {
        textTituloPlato?.text = pedido.nombre
        textCnatidadPlatos?.text = pedido.categoria

        val options = RequestOptions()
            .placeholder(R.drawable.fondopantalla)
            .error(R.drawable.fondopantalla)

        imagenPlato?.let {
            Glide.with(it)
                .setDefaultRequestOptions(options)
                .load(pedido.imagen)
                .into(it)
        }
    }
}