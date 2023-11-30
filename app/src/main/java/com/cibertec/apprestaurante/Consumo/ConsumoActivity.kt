package com.cibertec.apprestaurante.Consumo

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cibertec.apprestaurante.Categoria.CategoriasActivity
import com.cibertec.apprestaurante.Mesa.MesaViewModel
import com.cibertec.apprestaurante.Productos.ProductosFirebase

import com.cibertec.apprestaurante.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ConsumoActivity : AppCompatActivity(),ConsumoAdapter.ItemClickConsumo {
    private var mesa: String = ""
    private var id: String = ""

    private lateinit var viewModel: MesaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumo)

        viewModel= ViewModelProvider(this)[MesaViewModel::class.java]
        id = intent.getStringExtra("id_mesa") ?: ""
        viewModel.getConsumoID(id)


        val texto=findViewById<TextView>(R.id.textView3)


        val recyclerNews=findViewById<RecyclerView>(R.id.recyclerConsumo)
        val adapter= ConsumoAdapter(this)
        recyclerNews.adapter=adapter
        recyclerNews.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
   //     val text_centro=findViewById<TextView>(R.id.tex_title)
        val btn_cancelacion =findViewById<Button>(R.id.btn_cancelacion)

        viewModel.listConsumoMutable.observe(this){listProd->
            if(listProd.isNotEmpty()){
                adapter.setConsumo(listProd)
                texto.visibility = View.GONE
                btn_cancelacion.isEnabled=true
            }


        }

       /* viewModel.listMesasMutable.observe(this) { listmesa ->
            if (listmesa.isNotEmpty()) {
                for (mesa in listmesa) {
                    val pagoTotalMesa = mesa.pago_total
                    pago.setText(pagoTotalMesa)

                }
            }
        }*/

        //obtenemos el numero de mesa
        mesa = intent.getStringExtra("mesa") ?: ""
     //   val titulo=findViewById<TextView>(R.id.titulo_consumo)
        //titulo.setText(mesa)


        val btn_add = findViewById<ImageButton>(R.id.btn_add_consumo)
        btn_add.setOnClickListener {
            val intent = Intent(this, CategoriasActivity::class.java)
            intent.putExtra("id_mesa", id)
            startActivity(intent)
        }


        val swipe = findViewById<SwipeRefreshLayout>(R.id.swipeC)
        swipe.setColorSchemeResources(R.color.cheleste,R.color.green)

        swipe.setOnRefreshListener {

            val intent = intent
            Handler(Looper.getMainLooper()).postDelayed({
                finish()
                startActivity(intent)
                swipe.isRefreshing=false
            },1500)

        }



        btn_cancelacion.setOnClickListener{
            alert()
        }

    }

    fun alert(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Esta Mesa contiene" )
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.alerta_pago, null)
        builder.setView(dialogLayout)

        val btnEfectuar = dialogLayout.findViewById<Button>(R.id.btn_efecutar)
        val tableLayout = dialogLayout.findViewById<TableLayout>(R.id.tableLayout)
        crear_tabla(tableLayout)


        val mAlertDialog = builder.show()

        btnEfectuar.setOnClickListener {
            Estado()
            mAlertDialog.dismiss()

        }

    }


    fun Estado(){
        val db = Firebase.firestore
        val documentId = id
        val campoAActualizar = "estado"
        val nuevoValor = "Cancelado"

        val referenciaDocumento = db.collection("orden").document(documentId)

        referenciaDocumento.update(campoAActualizar, nuevoValor)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
            }
    }


   fun crear_tabla(tabla:TableLayout){
       var totalAPagar = 0.0
       // Establecer los márgenes entre las celdas
       val params = TableRow.LayoutParams(
           TableRow.LayoutParams.WRAP_CONTENT,
           TableRow.LayoutParams.WRAP_CONTENT
       )
       params.setMargins(10, 10, 10, 10) // Ajusta los márgenes según tu preferencia
       val size=resources.getDimension(R.dimen.tamaño_texto_dp)
       // Crear fila para encabezados
       val headerRow = TableRow(this)

       val nombreHeader = TextView(this)
       nombreHeader.text = "Nombre"
       nombreHeader.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size)
       val cantHeader = TextView(this)
       cantHeader.text = "Cantidad"
       cantHeader.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size)
       val precioHeader = TextView(this)
       precioHeader.text = "Precio"
       precioHeader.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size)
       val importHeader = TextView(this)
       importHeader.text = "Importe"
       importHeader.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size)
       // Aplicar los márgenes a cada encabezado
       nombreHeader.layoutParams = params
       cantHeader.layoutParams = params
       precioHeader.layoutParams = params
       importHeader.layoutParams = params

       // Agregar encabezados a la fila
       headerRow.addView(nombreHeader)
       headerRow.addView(cantHeader)
       headerRow.addView(precioHeader)
       headerRow.addView(importHeader)


       // Agregar la fila de encabezados a la tabla
       tabla.addView(headerRow)

       // Observar los cambios en la lista de consumos
       viewModel.listConsumoMutable.observe(this) { listcons ->
           if (listcons.isNotEmpty()) {
               for (cons in listcons) {
                   val nombre = cons.nombre
                   val cant = cons.cantidad
                   val precio = cons.precio
                   val totalProducto = cant * precio
                   totalAPagar += totalProducto

                   val tableRow = TableRow(this)

                   val nombreTextView = TextView(this)
                   nombreTextView.text = nombre
                   nombreTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size)
                   val cantTextView = TextView(this)
                   cantTextView.text = cant.toString()
                   cantTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size)
                   val precioTextView = TextView(this)
                   precioTextView.text = precio.toString()
                   precioTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size)

                   val totalPrecio = cant * precio // Calcular el precio total por producto

                   val totalPrecioTextView = TextView(this)
                   totalPrecioTextView.text = totalPrecio.toString()
                   totalPrecioTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size)

                   // Agregar los TextViews a la fila
                   tableRow.addView(nombreTextView)
                   tableRow.addView(cantTextView)
                   tableRow.addView(precioTextView)
                   tableRow.addView(totalPrecioTextView) // Agregar la columna del total por producto

                   // Agregar la fila a la tabla
                   tabla.addView(tableRow)
               }

               // Crear una nueva fila para mostrar el total a pagar
               val totalRow = TableRow(this)

               val totalLabel = TextView(this)
               totalLabel.text = "Total a pagar:"
               totalLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size)
               val totalValue = TextView(this)
               totalValue.text = totalAPagar.toString()
               totalValue.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size)

               // Agregar los TextViews a la fila del total a pagar
               totalRow.addView(totalLabel)
               totalRow.addView(totalValue)

               // Agregar la fila del total a pagar a la tabla
               tabla.addView(totalRow)
           }
       }

    }



    override fun onItemClick(consumo: ProductosFirebase) {
        TODO("Not yet implemented")
    }

}