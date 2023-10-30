package com.cibertec.apprestaurante.Categoria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Activity.CategoriasActivity
import com.cibertec.apprestaurante.database.Categoria

class CategoriaAdapter(val mItemClickListener: CategoriasActivity):
RecyclerView.Adapter<CategoriaViewHolder>(){
    private var categoriaLis = emptyList<Categoria>()

    fun setCategoria(categorias: List<Categoria>){
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
        val categoria: Categoria = categoriaLis[position]
        holder.bind(categoria)

    }


}