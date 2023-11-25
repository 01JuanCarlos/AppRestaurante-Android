package com.cibertec.apprestaurante.Productos

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.apprestaurante.Categoria.CategoriaViewModel
import com.cibertec.apprestaurante.R
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FoodsActiviity: AppCompatActivity(), ProductoAdapter.ItemClickPlatos  {

    private var nombre: String = ""
    private var id: String = ""
    private var id_mesa: String = ""


    private lateinit var ViewModel: CategoriaViewModel
    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foods)

        id_mesa = intent.getStringExtra("id_mesa") ?: ""

        ViewModel = run{
            ViewModelProvider(this)[CategoriaViewModel::class.java]
        }
        id = intent.getStringExtra("id") ?: ""

        ViewModel.getCategoriasID(id)

        val recyclerFoods = findViewById<RecyclerView>(R.id.recyclerFoods)
        val adapter = ProductoAdapter(this)
        recyclerFoods.adapter = adapter
        recyclerFoods.layoutManager = LinearLayoutManager(this)

        ViewModel.listProductosMutable.observe(this){listProd->
            if(listProd.isNotEmpty()){
                adapter.setProduct(listProd)
            }
        }


    }




    private fun GuardarFirestore(
        nombre: String,
        precio: String,
        cantidad: Int,
        especif: String,
        fecha: String
    ){
        //  val fecha=formatDate(LocalDateTime.now())

        println("ID_MESASSSS::::::  $id_mesa")

        firestore = FirebaseFirestore.getInstance()

        val nuevoProducto = ProductosFirebase(nombre,precio,cantidad,especif,fecha)
        println("CONSUMO::::::  $nuevoProducto")
        val nuevoProductoMap = nuevoProducto.toMap()


        val docRef = firestore.collection("orden").document(id_mesa)

        docRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {

                val pagoTotal = documentSnapshot.getString("pago_total")
                if (pagoTotal != null) {
                    // Utiliza el valor de pagoTotal aquí
                    println("Pago total: $pagoTotal")
                }


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




    override fun onItemClick(consumo: ProductosFirebase) {
       //  println("CONSUMOOOOOOO:: "+consumo.nombre)
        val nombre=consumo.nombre
        val precio=consumo.precio
        val descripcion=consumo.descrip
        val fecha=formatDate(LocalDateTime.now())

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Agregar  Consumo" )

        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.alerta_produc, null)

        val txt_Info = dialogLayout.findViewById<TextView>(R.id.txt_info)
        var info= "Nombre: $nombre\nPrecio: $precio\nDescripcion: $descripcion"

        txt_Info.setText(info)

        builder.setView(dialogLayout)

        var cantidad = dialogLayout.findViewById<EditText>(R.id.edt_Cantidad)
        val especif = dialogLayout.findViewById<EditText>(R.id.edt_Especi)

        val btnAgregar = dialogLayout.findViewById<Button>(R.id.btn_Agregar)
        val mAlertDialog = builder.show()

        btnAgregar.setOnClickListener {
            mAlertDialog.dismiss()
            val cant=cantidad.text.toString().toInt()
            val espe=especif.text.toString()
            println("cantido: "+cant+" especific: "+espe+" fecha "+fecha)

            GuardarFirestore(nombre,precio,cant,espe,fecha)

        }

    }
    fun formatDate(date: LocalDateTime): String {
        // 02/10/2023 15:54:00
        val format = "dd/MM/yyyy HH:mm:ss"
        return date.format(DateTimeFormatter.ofPattern(format))
    }
}