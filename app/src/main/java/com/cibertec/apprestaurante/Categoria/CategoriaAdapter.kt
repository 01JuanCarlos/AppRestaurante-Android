package com.cibertec.apprestaurante.Categoria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CategoriaAdapter(val mItemClickListener: ItemClickCategoria):
RecyclerView.Adapter<CategoriaViewHolder>(){

    private var categoriaLis = emptyList<CategoriaFirestore>()

    fun setCategoria(categorias: List<CategoriaFirestore>){
        this.categoriaLis = categorias
        this.notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoriaViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return categoriaLis.size
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria: CategoriaFirestore = categoriaLis[position]
        holder.bind(categoria)

        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(categoria)
        }
    }

    interface ItemClickCategoria{
        fun onItemClick(categoria: CategoriaFirestore)

    }

}