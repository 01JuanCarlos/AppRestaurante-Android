package com.cibertec.apprestaurante.Activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cibertec.apprestaurante.Adapter.MesaAdapter
import com.cibertec.apprestaurante.Model.Mesa
import com.cibertec.apprestaurante.R

class MesasActivity : AppCompatActivity(){

    private val listMesa= listOf(
        Mesa(1,"Juan Miranda"),
        Mesa(2,"Tony Estrella"),
        Mesa(3,"Andres"),
        Mesa(4,"Pepe"),
        Mesa(5,"Miguel"),
        Mesa(6,"Raul"),
        Mesa(7,"Pepe"),
        Mesa(8,"Miguel"),
        Mesa(9,"Raul"),
        Mesa(10,"Pepe"),
        Mesa(11,"Miguel"),
        Mesa(12,"Raul"),

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesas)

        val recyclerNews = findViewById<RecyclerView>(R.id.recyclerNews)
        recyclerNews.apply {
            // layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = GridLayoutManager(context, 2)
           // layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = MesaAdapter(listMesa)
        }
        val btnAdd = findViewById<ImageView>(R.id.btn_add)
        btnAdd.setOnClickListener {
            alertWithDesignCustom()
        }
    }
    fun alertWithDesignCustom() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alerta con Diseño Personalizado")

        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.alert, null)
        builder.setView(dialogLayout)

        val edtTitle = dialogLayout.findViewById<EditText>(R.id.edtTitleNote)
        val btnCreate = dialogLayout.findViewById<Button>(R.id.btnCreate)

        val mAlertDialog = builder.show()

        btnCreate.setOnClickListener {
            val title = edtTitle.text.toString()
            Toast.makeText(this, title, Toast.LENGTH_LONG).show()

            mAlertDialog.dismiss()
        }

    }

}