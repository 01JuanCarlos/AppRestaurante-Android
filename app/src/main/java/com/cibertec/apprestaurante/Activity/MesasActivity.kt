package com.cibertec.apprestaurante.Activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Adapter.MesaAdapter
import com.cibertec.apprestaurante.database.Mesa
import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.ViewModel.MesaViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MesasActivity : AppCompatActivity(),MesaAdapter.ItemClickListener{

    private lateinit var mesaViewModel:MesaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesas)

        mesaViewModel=run {
            ViewModelProvider(this)[MesaViewModel::class.java]
        }

        val btnAdd = findViewById<ImageView>(R.id.btn_add)
        btnAdd.setOnClickListener {
            RegistrarMesa()
        }

        val recyclerMesas = findViewById<RecyclerView>(R.id.recyclerMesas)
        val adapter=MesaAdapter(this)
        recyclerMesas.adapter=adapter
        recyclerMesas.layoutManager=LinearLayoutManager(this)

        mesaViewModel.mesas?.observe(this){ mesas->
            mesas?.let{
                adapter.setMesa(mesas)
            }
        }
    }




    fun RegistrarMesa() {


        val builder = AlertDialog.Builder(this)
        builder.setTitle("Agregar nueva mesa")

        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.alert, null)
        builder.setView(dialogLayout)

        val edtNombre = dialogLayout.findViewById<EditText>(R.id.edtNombre)

        val edtNumero = dialogLayout.findViewById<EditText>(R.id.edtNumero)

        val btnCreate = dialogLayout.findViewById<Button>(R.id.btnCreate)
        val mAlertDialog = builder.show()

        btnCreate.setOnClickListener {
            mAlertDialog.dismiss()
            val nombre = edtNombre.text.toString()
            val numero = edtNumero.text.toString().toInt()
            val date=formatDate(LocalDateTime.now())

            var mesa=Mesa(numero,nombre,date)
            mesaViewModel.saveMesaWithCoroutines(mesa)
          //  Toast.makeText(this, nombre, Toast.LENGTH_LONG).show()
        }

    }

    fun formatDate(date: LocalDateTime): String {
        // 02/10/2023 15:54:00
        val format = "dd/MM/yyyy HH:mm:ss"
        return date.format(DateTimeFormatter.ofPattern(format))
    }

    override fun onItemClick(mesa: Mesa) {

    }
}