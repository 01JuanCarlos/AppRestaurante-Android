package com.cibertec.apprestaurante.Platos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Activity.FoodsActiviity
import com.cibertec.apprestaurante.database.Plato


class PlatoAdapter(val mItemClickListener: FoodsActiviity):
RecyclerView.Adapter<PlatoViewHolder>(){
    private var platoList = emptyList<Plato>()

    fun setPlato(platos: List<Plato>){
        this.platoList = platos
        this.notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlatoViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return platoList.size
    }

    override fun onBindViewHolder(holder: PlatoViewHolder, position: Int) {
        val plato: Plato = platoList[position]
        holder.bind(plato)
    }
}