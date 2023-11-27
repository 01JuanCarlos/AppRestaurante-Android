package com.cibertec.apprestaurante.Mesa

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.cibertec.apprestaurante.database.Mesa
import com.cibertec.apprestaurante.R

class MesaViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(
    R.layout.item_mesas, parent, false)){


    private var textNumero: TextView? = null
    private var textNombre: TextView? = null
    private var textEstado: TextView? = null



    init {
        textNumero = itemView.findViewById(R.id.text_numero_mea)
        textNombre = itemView.findViewById(R.id.text_nombre)
        textEstado=itemView.findViewById(R.id.text_estado_C)
    }

    fun bind(mesa: MesaFirebase) {
        textNumero?.text ="N° "+mesa.numero.toString()
        textNombre?.text = mesa.nombre
        textEstado?.text=mesa.estado

     /*   if(mesa.estado contentEquals "En espera"){
            textEstado?.setBackgroundColor(Color.parseColor("#1AE62A"))

        }
*/
        when (mesa.estado) {
            "En espera" -> textEstado?.setBackgroundColor(Color.parseColor("#FF0F0F"))
            "Atendido" -> textEstado?.setBackgroundColor(Color.parseColor("#FF990F"))
            "Cancelado" -> textEstado?.setBackgroundColor(Color.parseColor("#1AE62A"))


            else -> println("Opción no reconocida")
        }





      /*  val options = RequestOptions()
            .placeholder(R.drawable.logo)
            .error(R.drawable.logo)
*/

    }
}