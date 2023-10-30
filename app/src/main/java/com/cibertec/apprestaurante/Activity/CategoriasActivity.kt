package com.cibertec.apprestaurante.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Categoria.CategoriaAdapter
import com.cibertec.apprestaurante.Categoria.CategoriaViewModel
import com.cibertec.apprestaurante.R

class CategoriasActivity: AppCompatActivity() {
    private lateinit var categoriaViewModel: CategoriaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)

        categoriaViewModel=run {
            ViewModelProvider(this)[CategoriaViewModel::class.java]
        }

        val recyclerCategorias = findViewById<RecyclerView>(R.id.recyclerCategorias)
        val adapter= CategoriaAdapter(this)
        recyclerCategorias.adapter=adapter
        recyclerCategorias.layoutManager= LinearLayoutManager(this)

        categoriaViewModel.categoria?.observe(this){ categorias->
            categorias?.let{
                adapter.setCategoria(categorias)
            }
        }

    }


}