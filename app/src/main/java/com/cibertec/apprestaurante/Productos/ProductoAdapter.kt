package com.cibertec.apprestaurante.Productos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.database.Plato


class ProductoAdapter(val mItemClickListener: ItemClickPlatos):
RecyclerView.Adapter<ProductoViewHolder>(){
    private var productList = emptyList<ProductosFirebase>()

    fun setProduct(product: List<ProductosFirebase>){
        this.productList = product
        this.notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductoViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto: ProductosFirebase = productList[position]
        holder.bind(producto)

        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(producto)
        }
    }
    interface ItemClickPlatos {
        fun onItemClick(producto: ProductosFirebase)
    }




}