package com.cibertec.apprestaurante.Productos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ProductoViewHolder (inflater: LayoutInflater, parent: ViewGroup):
RecyclerView.ViewHolder(inflater.inflate(R.layout.item_foods, parent, false)){

    private var textTitle: TextView? = null
    private var imagen: ImageView? = null
    private var textPrice: TextView? = null
    private var textDescription: TextView? = null

    init {
        imagen=itemView.findViewById(R.id.imgPort)
        textTitle = itemView.findViewById(R.id.textTit)
        textPrice = itemView.findViewById(R.id.textPri)
        textDescription = itemView.findViewById(R.id.textDesc)
    }

    fun bind(producto: ProductosFirebase){
        textTitle?.text = producto.nombre
        textPrice?.text = producto.precio.toString()
        textDescription?.text = producto.descrip


        val options = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        imagen?.let {
            Glide.with(it)
                .setDefaultRequestOptions(options)
                .load(producto.imagen)
                .into(it)
        }
    }
}