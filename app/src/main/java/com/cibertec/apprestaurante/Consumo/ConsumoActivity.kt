package com.cibertec.apprestaurante.Consumo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Activity.CategoriasActivity
import com.cibertec.apprestaurante.Mesa.MesaAdapter
import com.cibertec.apprestaurante.Mesa.MesaFirebase
import com.cibertec.apprestaurante.Mesa.MesaViewModel
import com.cibertec.apprestaurante.Productos.ProductosFirebase

import com.cibertec.apprestaurante.R
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConsumoActivity : AppCompatActivity(),ConsumoAdapter.ItemClickConsumo {
    private var mesa: String = ""
    private var id: String = ""

    private lateinit var viewModel: MesaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumo)

        viewModel= ViewModelProvider(this)[MesaViewModel::class.java]
        id = intent.getStringExtra("id") ?: ""

        viewModel.getConsumoID(id)


        val recyclerNews=findViewById<RecyclerView>(R.id.recyclerConsumo)
        val adapter= ConsumoAdapter(this)
        recyclerNews.adapter=adapter
        recyclerNews.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        viewModel.listConsumoMutable.observe(this){listProd->
            if(listProd.isNotEmpty()){
                adapter.setConsumo(listProd)
            }
        }



        mesa = intent.getStringExtra("mesa") ?: ""
        //println("MESAAAAAAAAA"+mesa)
        val titulo=findViewById<TextView>(R.id.titulo_consumo)
        titulo.setText(mesa)


        //val btn_add = findViewById<ImageButton>(R.id.btn_add)
      /*  btn_add.setOnClickListener {
            startActivity(Intent(this, CategoriasActivity:: class.java))
        }
*/


    }

    private fun GuardarFirestore(coleccion:String,nombre:String,numero:Int){
        var dbRestaurante= FirebaseFirestore.getInstance()
        val fecha=formatDate(LocalDateTime.now())
        dbRestaurante.collection(coleccion).document().set(
            hashMapOf(
                "nombre" to nombre,
                "mesa" to numero,
                "fecha" to fecha
            )
        )
    }
    fun formatDate(date: LocalDateTime): String {
        // 02/10/2023 15:54:00
        val format = "dd/MM/yyyy HH:mm:ss"
        return date.format(DateTimeFormatter.ofPattern(format))
    }





    override fun onItemClick(consumo: ProductosFirebase) {
        TODO("Not yet implemented")
    }

}