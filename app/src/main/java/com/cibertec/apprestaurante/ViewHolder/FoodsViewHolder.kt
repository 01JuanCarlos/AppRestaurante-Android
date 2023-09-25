package com.cibertec.apprestaurante.ViewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Model.Foods
import com.cibertec.apprestaurante.R

class FoodsViewHolder (inflater: LayoutInflater, parent: ViewGroup):
RecyclerView.ViewHolder(inflater.inflate(R.layout.item_foods, parent, false)){
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

    fun bind(foods: Foods){
        imgFood?.setImageResource(foods.image)
        textTitle?.text = foods.title
        textPrice?.text = foods.price
        textDescription?.text = foods.description
    }
}