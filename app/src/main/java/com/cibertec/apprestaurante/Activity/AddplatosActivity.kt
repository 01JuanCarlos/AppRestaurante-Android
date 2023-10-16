package com.cibertec.apprestaurante.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.apprestaurante.R

class AddplatosActivity: AppCompatActivity() {

    private var ListaCategoria: Spinner?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_platos)

        ListaCategoria=findViewById(R.id.ListaCategoria)

        val listaCategoria= arrayOf("Seleccione","Desayuno","Almuerzos","Postres","Bebidas")

        var adaptador: ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_spinner_item,listaCategoria)
        ListaCategoria?.adapter=adaptador
}}