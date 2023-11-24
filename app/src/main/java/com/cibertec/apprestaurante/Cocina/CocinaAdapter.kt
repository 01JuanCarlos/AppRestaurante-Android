package com.cibertec.apprestaurante.Cocina


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Mesa.MesaFirebase
import com.cibertec.apprestaurante.database.Mesa


class CocinaAdapter(val  mItemClickListener: ItemClickListener):
    RecyclerView.Adapter<CocinaViewHolder>()
{
    private var mesaList= emptyList<MesaFirebase>()

    fun setMesa(mesa:List<MesaFirebase>){
        this.mesaList=mesa
        this.notifyDataSetChanged()
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocinaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CocinaViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return mesaList.size
    }

    override fun onBindViewHolder(holder: CocinaViewHolder, position: Int) {
        val mesa: MesaFirebase = mesaList[position]
        holder.bind(mesa)
        holder.itemView.setOnClickListener{
            mItemClickListener.onItemClick(mesa)
        }
    }

    interface ItemClickListener{
        fun onItemClick(mesa: MesaFirebase)
    }
}