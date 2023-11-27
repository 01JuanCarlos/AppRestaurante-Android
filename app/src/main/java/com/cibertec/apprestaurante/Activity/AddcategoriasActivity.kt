package com.cibertec.apprestaurante.Activity

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cibertec.apprestaurante.Categoria.CategoriaViewModel
import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.database.Categoria


class AddcategoriasActivity: AppCompatActivity() {

    private lateinit var categoriaViewModel: CategoriaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_categorias)

        val categoria=findViewById<EditText>(R.id.nombrecategoria)

        categoriaViewModel=run {
            ViewModelProvider(this)[CategoriaViewModel::class.java]
        }



        val btnGuardar = findViewById<ImageButton>(R.id.btnGuardar)
        btnGuardar.setOnClickListener {
            categoriaViewModel.crearCategoria(categoria.text.toString())
            categoria.setText("")
        }

    }



}