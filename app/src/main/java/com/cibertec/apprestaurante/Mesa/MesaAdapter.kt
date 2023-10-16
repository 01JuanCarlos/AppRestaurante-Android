package com.cibertec.apprestaurante.Mesa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.database.Mesa

class MesaAdapter(val  mItemClickListener: ItemClickListener):
RecyclerView.Adapter<MesaViewHolder>() {



    private var mesaList= emptyList<Mesa>()

    fun setMesa(mesa:List<Mesa>){
        this.mesaList=mesa
      this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MesaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MesaViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return mesaList.size
    }

    override fun onBindViewHolder(holder: MesaViewHolder, position: Int) {
        val mesa: Mesa = mesaList[position]
        holder.bind(mesa)
        holder.itemView.setOnClickListener{
            mItemClickListener.onItemClick(mesa)
        }
    }
    interface ItemClickListener{
        fun onItemClick(mesa:Mesa)
    }

}