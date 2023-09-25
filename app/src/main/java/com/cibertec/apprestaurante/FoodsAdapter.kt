package com.cibertec.apprestaurante

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FoodsAdapter(val list: List<Foods>):
    RecyclerView.Adapter<FoodsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FoodsViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        val foods: Foods = list[position]
        holder.bind(foods)
    }
}