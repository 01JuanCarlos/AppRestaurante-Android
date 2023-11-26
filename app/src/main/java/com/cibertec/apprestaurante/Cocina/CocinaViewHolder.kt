package com.cibertec.apprestaurante.Cocina

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.cibertec.apprestaurante.Mesa.MesaFirebase
import com.cibertec.apprestaurante.database.Mesa
import com.cibertec.apprestaurante.R

class CocinaViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(
        R.layout.item_cocina, parent, false))
{

    private var textMesa: TextView? = null
    private var numeromesaCocina: TextView? = null
    private var estado: TextView? = null


    init {
        estado=itemView.findViewById(R.id.text_estado)
        textMesa = itemView.findViewById(R.id.textMesa)
        numeromesaCocina = itemView.findViewById(R.id.numeromesaCocina)    }


    fun bind(mesa: MesaFirebase) {

        textMesa?.text = mesa.nombre
        numeromesaCocina?.text = mesa.numero.toString()
        estado?.text=mesa.estado
        when (mesa.estado) {
            "En espera" -> estado?.setBackgroundColor(Color.parseColor("#FF0F0F"))
            "Atendido" -> estado?.setBackgroundColor(Color.parseColor("#FF990F"))
            "Cancelado" -> estado?.setBackgroundColor(Color.parseColor("#1AE62A"))


            else -> println("Opción no reconocida")
        }

        val options = RequestOptions()
            .placeholder(R.drawable.mesas)
            .error(R.drawable.mesas)
    }
}