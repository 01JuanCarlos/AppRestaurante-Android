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

        viewModel.listConsumoMutable.observe(this){listProd->
            if(listProd.isNotEmpty()){
                adapter.setConsumo(listProd)
            }
        }



        mesa = intent.getStringExtra("mesa") ?: ""
        //println("MESAAAAAAAAA"+mesa)
        val titulo=findViewById<TextView>(R.id.titulo_consumo)
        titulo.setText(mesa)


        val btn_add = findViewById<ImageButton>(R.id.btn_add_consumo)


        btn_add.setOnClickListener {

            val intent = Intent(this, CategoriasActivity::class.java)
            println("ID-Consumo: "+id)
            intent.putExtra("id_mesa", id)
            startActivity(intent)
            //  RegistrarConsumo(0)
        }


        val btn_recargar = findViewById<Button>(R.id.btn_recargar)

        btn_recargar.setOnClickListener{
            val intent = intent
            finish() // Finaliza la actividad actual
            startActivity(intent) //
        }

    }





    fun RegistrarConsumo(tipo : Int) {

/*
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Agregar  Consumo" )

        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.alerta_produc, null)
        builder.setView(dialogLayout)

        val edtnombre = dialogLayout.findViewById<EditText>(R.id.ect_prod)
        val edtprecio = dialogLayout.findViewById<EditText>(R.id.edt_precio)


        val btnAgregar = dialogLayout.findViewById<Button>(R.id.btn_Agregar)
        val mAlertDialog = builder.show()

        /*if(tipo==1){
            edtNombre.setText(mesa?.nombre)
            edtNumero.setText(mesa?.numero.toString())
        }
*/
        btnAgregar.setOnClickListener {
            mAlertDialog.dismiss()
            var nombre = edtnombre.text.toString()
            var precio = edtprecio.text.toString()






            if(tipo==0){
                //var mesa=MesaFirebase(nombre,numero)
               GuardarFirestore("orden",nombre,precio)
                //     viewModel.saveMesaWithCoroutines(mesa)
            }else{
                /* var mesaUpdate=Mesa(numero,nombre,date)
                 mesaUpdate.Id_mesa=mesa?.Id_mesa!!
                 mesaViewModel.upateMesaWithCoroutines(mesaUpdate)*/
            }


            //  Toast.makeText(this, nombre, Toast.LENGTH_LONG).show()
        }
*/
    }





    private fun GuardarFirestore(coleccion:String,nombre:String,precio:String){
        //  val fecha=formatDate(LocalDateTime.now())
        firestore = FirebaseFirestore.getInstance()

        val nuevoProducto = ProductosFirebase(nombre, precio)

        val nuevoProductoMap = nuevoProducto.toMap()


        val docRef = firestore.collection(coleccion).document(id)

        docRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val consumoActual = documentSnapshot.get("consumo") as? List<Map<String, Any>> ?: listOf()

                // Agregar el nuevo producto al array existente
                val nuevoConsumo = consumoActual.toMutableList()
                nuevoConsumo.add(nuevoProductoMap as Map<String, Any>)

                // Actualizar el campo 'consumo' en Firestore con el nuevo array
                docRef.update("consumo", nuevoConsumo)
                    .addOnSuccessListener {
                        // Éxito al guardar el nuevo producto en el array
                    }
                    .addOnFailureListener { e ->
                        // Error al guardar el nuevo producto en el array
                    }
            } else {
                // El documento con el ID especificado no existe
            }
        }.addOnFailureListener { e ->
            // Error al obtener el documento
        }

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