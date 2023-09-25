package com.cibertec.apprestaurante.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.Model.Pedidos

class ViewHolderPedidos (inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(
        R.layout.item_platos, parent,
        false))
{
    private var imgPedido: ImageView? = null
    private var texNombrePlato: TextView? = null
    private var textCantidad: TextView? = null

    init
    {
        imgPedido = itemView.findViewById(R.id.imgJugo)
        texNombrePlato = itemView.findViewById(R.id.P1)
        textCantidad = itemView.findViewById(R.id.D1)
    }

    fun bind(pedidos: Pedidos)
    {
        texNombrePlato?.text = pedidos.Title
        textCantidad?.text = pedidos.cantidad
        imgPedido?.setImageResource(pedidos.img)
    }

}