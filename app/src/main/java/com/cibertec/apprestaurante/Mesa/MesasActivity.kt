package com.cibertec.apprestaurante.Mesa


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cibertec.apprestaurante.Consumo.ConsumoActivity
import com.cibertec.apprestaurante.R
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MesasActivity : AppCompatActivity(), MesaAdapter.ItemClickMesa {

    private lateinit var viewModel: MesaViewModel
    private lateinit var firestore: FirebaseFirestore
    private var numeros = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesas)

        viewModel=ViewModelProvider(this)[MesaViewModel::class.java]
        viewModel.getMesasFirebase()

        val recyclerNews=findViewById<RecyclerView>(R.id.recyclerMesas)
        val adapter=MesaAdapter(this)

        val espera=findViewById<Button>(R.id.btn_espera)
        val atendido=findViewById<Button>(R.id.btn_atend)
        val cancelado=findViewById<Button>(R.id.btn_cancel)


        espera.setOnClickListener{
            viewModel.getEstado("En espera")
        }
        atendido.setOnClickListener{
            viewModel.getEstado("Atendido")
        }
        cancelado.setOnClickListener{
            viewModel.getEstado("Cancelado")
        }


        recyclerNews.adapter=adapter
        recyclerNews.layoutManager=LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        viewModel.listMesasMutable.observe(this){listMesas->
            for (m in listMesas) {
                val numero = m.numero
                numeros.add(numero)
            }
            if(listMesas.isNotEmpty()){
                adapter.setMesa(listMesas)
            }
        }


        val btnadd = findViewById<ImageButton>(R.id.btn_add)
        btnadd.setOnClickListener{
            RegistrarMesa()
        }




        configSwipe()

    }
    fun configSwipe(){
        val swipe =findViewById<SwipeRefreshLayout>(R.id.swipe)
        swipe.setColorSchemeResources(R.color.cheleste,R.color.green)
        swipe.setOnRefreshListener {
            val intent = intent
            Handler(Looper.getMainLooper()).postDelayed({
                finish()
                startActivity(intent)
                swipe.isRefreshing=false
            },1500)

        }
    }



    fun RegistrarMesa() {

        val titleAlertMesa="Agregar nueva mesa"

        val builder = AlertDialog.Builder(this)
        builder.setTitle(titleAlertMesa)

        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.alert, null)
        builder.setView(dialogLayout)

        val edtNombre = dialogLayout.findViewById<EditText>(R.id.edtNombre)

        val edtNumero = dialogLayout.findViewById<EditText>(R.id.edtNumero)

        val btnCreate = dialogLayout.findViewById<Button>(R.id.btnCreate)
        val mAlertDialog = builder.show()


        btnCreate.setOnClickListener {



            mAlertDialog.dismiss()
            var nombre = edtNombre.text.toString()
            val numero = edtNumero.text.toString().toInt()
            if (numeros.contains(numero)) {

                val builder = AlertDialog.Builder(this)

                builder.setTitle("ADVERTENCIA")
                builder.setMessage("EL NUMERO DE MESA YA SE ENCUENTRA REGISTRADO")

                builder.setPositiveButton("Aceptar") { dialog, which ->
                }

                val dialog = builder.create()
                dialog.show()

            } else {


                GuardarFirestore(nombre,numero)
                val builder = AlertDialog.Builder(this)

                builder.setTitle("ORDEN AGREGADA")
               builder.setMessage("")
                val dialog = builder.create()
                dialog.show()

                builder.setPositiveButton("Aceptar") { dialog, which ->
                }
               // println("El número $numero no está en el array.")
            }





        }

    }



    private fun GuardarFirestore(nombre:String,numero:Int){

      //  val fecha=formatDate(LocalDateTime.now())

        firestore = FirebaseFirestore.getInstance()

        val mesa = hashMapOf<String, Any>(
            "nombre" to nombre,
            "mesa" to numero,
            "estado" to "En espera"
        )
      firestore.collection("orden")
            .add(mesa)
            .addOnSuccessListener { documentReference ->
            }
            .addOnFailureListener { e ->
            }


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
        val pago=mesa.pago_total
        val intent = Intent(this, ConsumoActivity::class.java)
        intent.putExtra("mesa", numero)
        intent.putExtra("id_mesa", id)
        intent.putExtra("pago_total", pago)

        startActivity(intent)
    }
}


