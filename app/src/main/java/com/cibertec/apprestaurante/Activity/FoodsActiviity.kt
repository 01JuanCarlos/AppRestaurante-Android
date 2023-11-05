package com.cibertec.apprestaurante.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Adapter.FoodsAdapter
import com.cibertec.apprestaurante.Plato.PlatoViewModel
import com.cibertec.apprestaurante.Platos.PlatoAdapter
import com.cibertec.apprestaurante.R

class FoodsActiviity: AppCompatActivity() {
    private lateinit var platosViewModel: PlatoViewModel
    /*
    private val listFoods = listOf(
        Foods(R.drawable.papaya,"Jugo de Papaya","S/15.00","Papaya con trosos de platano"),
        Foods(R.drawable.ensalada,"Ensalada de Frutas","S/15.00","Ensalada de frutas con cereal y jugo de naranaj"),
        Foods(R.drawable.palta,"Ensalada de Palta","S/20.00","Palta con pan y jugo de manzana"),
                Foods(R.drawable.papaya,"Jugo de Papaya","S/15.00","Papaya con trosos de platano"),
    Foods(R.drawable.ensalada,"Ensalada de Frutas","S/15.00","Ensalada de frutas con cereal y jugo de naranaj"),
    Foods(R.drawable.palta,"Ensalada de Palta","S/20.00","Palta con pan y jugo de manzana"),
        Foods(R.drawable.papaya,"Jugo de Papaya","S/15.00","Papaya con trosos de platano"),
        Foods(R.drawable.ensalada,"Ensalada de Frutas","S/15.00","Ensalada de frutas con cereal y jugo de naranaj"),
        Foods(R.drawable.palta,"Ensalada de Palta","S/20.00","Palta con pan y jugo de manzana")
    )*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foods)

        platosViewModel = run{
            ViewModelProvider(this)[PlatoViewModel::class.java]
        }

        val recyclerFoods = findViewById<RecyclerView>(R.id.recyclerFoods)
        val adapter = PlatoAdapter(this)
        recyclerFoods.adapter = adapter
        recyclerFoods.layoutManager = LinearLayoutManager(this)

        platosViewModel.plato?.observe(this){platos ->
            platos?.let {
                adapter.setPlato(platos)
            }
        }
    }
}