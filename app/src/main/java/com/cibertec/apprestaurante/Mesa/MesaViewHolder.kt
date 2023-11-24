package com.cibertec.apprestaurante.Mesa

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.cibertec.apprestaurante.database.Mesa
import com.cibertec.apprestaurante.R

class MesaViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(
    R.layout.item_mesas, parent, false)){


    private var textNumero: TextView? = null
    private var textNombre: TextView? = null


    init {
        textNumero = itemView.findViewById(R.id.textnumero)
        textNombre = itemView.findViewById(R.id.textnombre)
    }

    fun bind(mesa: MesaFirebase) {
        textNumero?.text = mesa.numero.toString()
        textNombre?.text = mesa.nombre

      /*  val options = RequestOptions()
            .placeholder(R.drawable.logo)
            .error(R.drawable.logo)
*/

    }
}