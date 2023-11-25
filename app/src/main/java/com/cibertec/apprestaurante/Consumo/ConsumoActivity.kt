package com.cibertec.apprestaurante.Consumo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Categoria.CategoriasActivity
import com.cibertec.apprestaurante.Mesa.MesaViewModel
import com.cibertec.apprestaurante.Productos.ProductosFirebase

import com.cibertec.apprestaurante.R
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConsumoActivity : AppCompatActivity(),ConsumoAdapter.ItemClickConsumo {
    private var mesa: String = ""
    private var id: String = ""
    private var pago_total: String = ""
    private lateinit var firestore: FirebaseFirestore

    private lateinit var viewModel: MesaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumo)

        viewModel= ViewModelProvider(this)[MesaViewModel::class.java]
        id = intent.getStringExtra("id_mesa") ?: ""
        viewModel.getConsumoID(id)





        val recyclerNews=findViewById<RecyclerView>(R.id.recyclerConsumo)
        val adapter= ConsumoAdapter(this)
        recyclerNews.adapter=adapter
        recyclerNews.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
   //     val text_centro=findViewById<TextView>(R.id.tex_title)

        viewModel.listConsumoMutable.observe(this){listProd->
            if(listProd.isNotEmpty()){
                adapter.setConsumo(listProd)
            }
        }
        val pago=findViewById<TextView>(R.id.text_pago)

        viewModel.listMesasMutable.observe(this) { listmesa ->
            if (listmesa.isNotEmpty()) {
                for (mesa in listmesa) {
                    val pagoTotalMesa = mesa.pago_total
                    pago.setText(pagoTotalMesa)

                }
            }
        }

        //obtenemos el numero de mesa
        mesa = intent.getStringExtra("mesa") ?: ""
        val titulo=findViewById<TextView>(R.id.titulo_consumo)
        titulo.setText(mesa)


        val btn_add = findViewById<ImageButton>(R.id.btn_add_consumo)
        btn_add.setOnClickListener {
            val intent = Intent(this, CategoriasActivity::class.java)
            intent.putExtra("id_mesa", id)
            startActivity(intent)
        }


        val btn_recargar = findViewById<Button>(R.id.btn_recargar)
        btn_recargar.setOnClickListener{
            val intent = intent

            finish()
            startActivity(intent)
        }

    }





    override fun onItemClick(consumo: ProductosFirebase) {
        TODO("Not yet implemented")
    }

}