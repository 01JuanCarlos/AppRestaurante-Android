package com.cibertec.apprestaurante.Categoria

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cibertec.apprestaurante.Consumo.ConsumoActivity
import com.cibertec.apprestaurante.Productos.FoodsActiviity
import com.cibertec.apprestaurante.R

class CategoriasActivity: AppCompatActivity(),CategoriaAdapter.ItemClickCategoria {

    private var id_mesa: String = ""
    private lateinit var ViewModel: CategoriaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)

        ViewModel=run {
            ViewModelProvider(this)[CategoriaViewModel::class.java]
        }
        ViewModel.getCategoriasFirebase()


        val recyclerCategorias = findViewById<RecyclerView>(R.id.recyclerCat)

        recyclerCategorias.apply {
            layoutManager = GridLayoutManager(context, 2)
        }

        val adapter= CategoriaAdapter(this)
        recyclerCategorias.adapter=adapter
        recyclerCategorias.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        ViewModel.listCategoriasMutable?.observe(this){ categorias->
            categorias?.let{
                adapter.setCategoria(categorias)
            }
        }



    }

    override fun onItemClick(categoria: CategoriaFirestore) {
        id_mesa = intent.getStringExtra("id_mesa") ?: ""

        val nombre = categoria.nombre
        val id=categoria.id


        val intent = Intent(this, FoodsActiviity::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("id", id)
        intent.putExtra("id_mesa", id_mesa)

        startActivity(intent)
    }


}