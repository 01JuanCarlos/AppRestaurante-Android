package com.cibertec.apprestaurante.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.ViewHolder.FoodsViewHolder
import com.cibertec.apprestaurante.Model.Foods

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