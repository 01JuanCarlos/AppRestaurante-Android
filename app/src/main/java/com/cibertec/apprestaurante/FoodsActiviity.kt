package com.cibertec.apprestaurante

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FoodsActiviity: AppCompatActivity() {

    private val listFoods = listOf(
        Foods(R.drawable.papaya,"Jugo de Papaya","S/15.00","Papaya con trosos de platano"),
        Foods(R.drawable.ensalada,"Ensalada de Frutas","S/15.00","Ensalada de frutas con cereal y jugo de naranaj"),
        Foods(R.drawable.palta,"Ensalada de Palta","S/20.00","Palta con pan y jugo de manzana")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foods)

        val recyclerFoods = findViewById<RecyclerView>(R.id.recyclerFoods)
        recyclerFoods.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = FoodsAdapter(listFoods)
        }
    }
}