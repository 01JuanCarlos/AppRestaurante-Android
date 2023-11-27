package com.cibertec.apprestaurante.Activity

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner


import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cibertec.apprestaurante.Categoria.CategoriaFirestore
import com.cibertec.apprestaurante.Categoria.CategoriaViewModel
import com.cibertec.apprestaurante.Plato.ProductViewModel

import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.database.Categoria
import com.cibertec.apprestaurante.database.Plato
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class AddplatosActivity:  AppCompatActivity() {

    private lateinit var platoViewModel: ProductViewModel
    private lateinit var categiriaViewModel: CategoriaViewModel

     var categoria: String=""
     var id: String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_platos)

           platoViewModel=run {
                ViewModelProvider(this)[ProductViewModel::class.java]
            }

            categiriaViewModel=run {
                ViewModelProvider(this)[CategoriaViewModel::class.java]
            }

            categiriaViewModel.getCategoriasFirebase()
            val spinner: Spinner = findViewById(R.id.editTextCategory)
            var arrayCategorias = emptyArray<String>()



        categiriaViewModel.listCategoriasMutable.observe(this) { categorias ->
            arrayCategorias = categorias.map { it.nombre }.toTypedArray()
            val adaptador: ArrayAdapter<String> = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                arrayCategorias
            )
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adaptador
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if ( arrayCategorias.isNotEmpty()) {
                    categoria = arrayCategorias[position]
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Implementación si es necesario
            }

        }


        val btnguardarplato = findViewById<ImageButton>(R.id.btnguardarplato)
        btnguardarplato.setOnClickListener() {
            val db = FirebaseFirestore.getInstance()

            val edtNombre = findViewById<EditText>(R.id.editTextName)
            var nombre = edtNombre.text.toString()
            val edtDescripcion = findViewById<EditText>(R.id.editTextDescription)
            val descripcion = edtDescripcion.text.toString()
            val edtPrecio = findViewById<EditText>(R.id.editTextPrecio)
            val precio = edtPrecio.text.toString()
            println("cAT:  $categoria")



            val plato = hashMapOf(
                "nombre_produc" to nombre,
                "descripcion" to descripcion,
                "precio" to precio,
            )



            //println("IDDDDD::::  "+id)
       /*     val categoriaRef = db.collection("categoria").document(id)
            categoriaRef.update("productos", FieldValue.arrayUnion(plato))
                .addOnSuccessListener {
                }
                .addOnFailureListener { e ->

                }*/



    /*
            if (plato !=null ) {
                showBasicAlertDialog(this,"Se agrego el plato exitosamente","Nombre: "+nombre+"\n"+"Categoria: "+categoria+"\n"+"Descripcion: "+descripcion+"\n"+"Precio: S/"+precio)
                edtNombre.setText("")
                edtDescripcion.setText("")
                edtPrecio.setText("")
            } else {
                showBasicAlertDialog(this,"Ingresa una categoria por favor","")
            }
*/

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