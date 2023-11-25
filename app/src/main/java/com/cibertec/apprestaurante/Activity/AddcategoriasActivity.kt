package com.cibertec.apprestaurante.Activity

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
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

        categoriaViewModel=run {
            ViewModelProvider(this)[CategoriaViewModel::class.java]
        }



        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        btnGuardar.setOnClickListener {

            val edtNombre = findViewById<EditText>(R.id.nombrecategoria)
            var nombre = edtNombre.text.toString()
            var categoria= Categoria(nombre)
            if (nombre!= "") {
            //    categoriaViewModel.saveCategoriaWithCoroutines(categoria)
                showBasicAlertDialog(this,"Categoria: "+nombre,"Agregada Exitosamente!!")
                edtNombre.setText("")
            } else {
                showBasicAlertDialog(this,"Ingresa una categoria por favor","")
            }

        }

    }
    fun showBasicAlertDialog(context: Context, title: String, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)

        // Agregar un botón "Aceptar"
        builder.setPositiveButton("Aceptar") { dialog, _ ->
            // Acción a realizar cuando se presiona "Aceptar"
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }


}