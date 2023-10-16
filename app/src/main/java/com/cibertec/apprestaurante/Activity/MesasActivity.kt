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
import com.cibertec.apprestaurante.Mesa.MesaAdapter
import com.cibertec.apprestaurante.database.Mesa
import com.cibertec.apprestaurante.R
import com.cibertec.apprestaurante.Mesa.MesaViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MesasActivity : AppCompatActivity(), MesaAdapter.ItemClickListener{

    private lateinit var mesaViewModel: MesaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesas)

        mesaViewModel=run {
            ViewModelProvider(this)[MesaViewModel::class.java]
        }

        val btnAdd = findViewById<ImageView>(R.id.btn_add)
        btnAdd.setOnClickListener {
            RegistrarMesa(null,tipo=0)
        }

        val recyclerMesas = findViewById<RecyclerView>(R.id.recyclerMesas)
        val adapter= MesaAdapter(this)
        recyclerMesas.adapter=adapter
        recyclerMesas.layoutManager=LinearLayoutManager(this)

        mesaViewModel.mesas?.observe(this){ mesas->
            mesas?.let{
                adapter.setMesa(mesas)
            }
        }
    }




    fun RegistrarMesa(mesa:Mesa?,tipo : Int) {

        val titleAlertMesa=if(tipo==0)"Agregar nueva mesa" else "Editar mesa"

        val builder = AlertDialog.Builder(this)
        builder.setTitle(titleAlertMesa)

        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.alert, null)
        builder.setView(dialogLayout)

        val edtNombre = dialogLayout.findViewById<EditText>(R.id.edtNombre)

        val edtNumero = dialogLayout.findViewById<EditText>(R.id.edtNumero)

        val btnCreate = dialogLayout.findViewById<Button>(R.id.btnCreate)
        val mAlertDialog = builder.show()

        if(tipo==1){
            edtNombre.setText(mesa?.nombre)
            edtNumero.setText(mesa?.numero.toString())
        }

        btnCreate.setOnClickListener {
            mAlertDialog.dismiss()
            val nombre = edtNombre.text.toString()
            val numero = edtNumero.text.toString().toInt()
            val date=formatDate(LocalDateTime.now())

            if(tipo==0){
                var mesa=Mesa(numero,nombre,date)
                mesaViewModel.saveMesaWithCoroutines(mesa)
            }else{
                var mesaUpdate=Mesa(numero,nombre,date)
                mesaUpdate.Id_mesa=mesa?.Id_mesa!!
                mesaViewModel.upateMesaWithCoroutines(mesaUpdate)
            }


          //  Toast.makeText(this, nombre, Toast.LENGTH_LONG).show()
        }

    }

    fun formatDate(date: LocalDateTime): String {
        // 02/10/2023 15:54:00
        val format = "dd/MM/yyyy HH:mm:ss"
        return date.format(DateTimeFormatter.ofPattern(format))
    }

    override fun onItemClick(mesa: Mesa) {
        RegistrarMesa(mesa,tipo=1)
    }
}