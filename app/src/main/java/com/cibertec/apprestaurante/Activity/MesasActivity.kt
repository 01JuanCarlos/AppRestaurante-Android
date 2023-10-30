package com.cibertec.apprestaurante.Activity


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
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
        val btnsaltar = findViewById<Button>(R.id.btn_saltar)
        btnsaltar.setOnClickListener {
            startActivity(Intent(this, CategoriasActivity::class.java))

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
            var nombre = edtNombre.text.toString()
            val numero = edtNumero.text.toString().toInt()
            val date=formatDate(LocalDateTime.now())

            if(tipo==0){
                var mesa=Mesa(numero,nombre,date)
                val url="http://192.168.100.10/AppRestaurante/insertar_datos.php"
                ejecutarServicio(url,numero,nombre)
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
   /*    val id= mesa.Id_mesa
        println("id : "+id)
        var consumo =startActivity(Intent(this, ConsumoActivity::class.java))
*/
        RegistrarMesa(mesa,tipo=1)
    }


    private fun ejecutarServicio(url: String,numero:Int,nombre:String) {

        val queue=Volley.newRequestQueue(this)
        var resultadoPost=object :StringRequest(Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this,"Mesa insertada exitosamente",Toast.LENGTH_SHORT).show()
            },Response.ErrorListener { error->
                Toast.makeText(this,"Error $error",Toast.LENGTH_LONG).show()
            }){
            override fun getParams():MutableMap<String,String>{
                val parametros=HashMap<String, String>()
                parametros.put("numero_mesa",numero.toString())
                parametros.put("nombre_mesa",nombre)
                return parametros
            }
        }
        queue.add(resultadoPost)
    }

}