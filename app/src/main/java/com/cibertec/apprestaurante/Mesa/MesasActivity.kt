package com.cibertec.apprestaurante.Mesa


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.cibertec.apprestaurante.Activity.CategoriasActivity
import com.cibertec.apprestaurante.Consumo.ConsumoActivity
import com.cibertec.apprestaurante.R
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MesasActivity : AppCompatActivity(), MesaAdapter.ItemClickMesa {

    private lateinit var viewModel: MesaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesas)

        viewModel=ViewModelProvider(this)[MesaViewModel::class.java]
        viewModel.getMesasFirebase()

        val recyclerNews=findViewById<RecyclerView>(R.id.recyclerMesas)
        val adapter=MesaAdapter(this)
        recyclerNews.adapter=adapter
        recyclerNews.layoutManager=LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        viewModel.listMesasMutable.observe(this){listMesas->
            if(listMesas.isNotEmpty()){
                adapter.setMesa(listMesas)
            }
        }
        val btnadd = findViewById<ImageButton>(R.id.btn_add)
        btnadd.setOnClickListener{
            RegistrarMesa(0)
        }


    }



    fun RegistrarMesa(tipo : Int) {

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

        /*if(tipo==1){
            edtNombre.setText(mesa?.nombre)
            edtNumero.setText(mesa?.numero.toString())
        }
*/
        btnCreate.setOnClickListener {
            mAlertDialog.dismiss()
            var nombre = edtNombre.text.toString()
            val numero = edtNumero.text.toString().toInt()

            if(tipo==0){
                //var mesa=MesaFirebase(nombre,numero)
                GuardarFirestore("orden",nombre,numero)
           //     viewModel.saveMesaWithCoroutines(mesa)
            }else{
               /* var mesaUpdate=Mesa(numero,nombre,date)
                mesaUpdate.Id_mesa=mesa?.Id_mesa!!
                mesaViewModel.upateMesaWithCoroutines(mesaUpdate)*/
            }


          //  Toast.makeText(this, nombre, Toast.LENGTH_LONG).show()
        }

    }



    private fun GuardarFirestore(coleccion:String,nombre:String,numero:Int){
        var dbRestaurante=FirebaseFirestore.getInstance()
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

/*
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
    }*/

    override fun onItemClick(mesa: MesaFirebase) {
        val numero = mesa.numero.toString()
        val id=mesa.id

        val intent = Intent(this, ConsumoActivity::class.java)
        intent.putExtra("mesa", numero)
        intent.putExtra("id", id)

        startActivity(intent)
    }
}