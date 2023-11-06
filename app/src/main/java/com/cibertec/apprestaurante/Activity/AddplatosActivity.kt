package com.cibertec.apprestaurante.Activity

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast


import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cibertec.apprestaurante.Categoria.CategoriaViewModel
import com.cibertec.apprestaurante.Plato.PlatoViewModel

import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.database.Categoria
import com.cibertec.apprestaurante.database.Plato
import java.text.FieldPosition

class AddplatosActivity:  AppCompatActivity() {
    private lateinit var platoViewModel:PlatoViewModel
    private lateinit var categoriaViewModel:CategoriaViewModel
    lateinit var categoria: String
    private lateinit var listaCategorias: ArrayList<Categoria>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_platos)

        var spinner = findViewById<Spinner>(R.id.editTextCategory)

        listaCategorias  = ArrayList()
        categoriaViewModel=run {
            ViewModelProvider(this)[CategoriaViewModel::class.java]
        }
        platoViewModel=run {
            ViewModelProvider(this)[PlatoViewModel::class.java]
        }

        spinner = findViewById(R.id.editTextCategory)

        val listaCategoria = listaCategorias

        var adaptador: ArrayAdapter<Categoria> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, listaCategoria)
        spinner?.adapter = adaptador

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {

                categoria = listaCategoria[position].toString()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }



        val btnguardarplato = findViewById<Button>(R.id.btnguardarplato)
        btnguardarplato.setOnClickListener() {

            val edtNombre = findViewById<EditText>(R.id.editTextName)
            var nombre = edtNombre.text.toString()
            val edtDescripcion = findViewById<EditText>(R.id.editTextDescription)
            val descripcion = edtDescripcion.text.toString()
            val edtPrecio = findViewById<EditText>(R.id.editTextPrecio)
            val precio = edtPrecio.text.toString().toDouble()


            var plato = Plato(nombre, categoria,  descripcion, precio)

            if (nombre!="" && descripcion!="" && categoria!="") {
                platoViewModel.savePlatoWithCoroutines(plato)
                showBasicAlertDialog(this,"Se agrego el plato exitosamente","Nombre: "+nombre+"\n"+"Categoria: "+categoria+"\n"+"Descripcion: "+descripcion+"\n"+"Precio: S/"+precio)
                edtNombre.setText("")
                edtDescripcion.setText("")
                edtPrecio.setText("")
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





