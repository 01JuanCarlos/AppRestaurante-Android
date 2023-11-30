package com.cibertec.apprestaurante.Pedidos

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cibertec.apprestaurante.Consumo.ConsumoAdapter
import com.cibertec.apprestaurante.Mesa.MesaViewModel
import com.cibertec.apprestaurante.R

import com.cibertec.apprestaurante.Productos.ProductosFirebase
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class PedidosActivity: AppCompatActivity(),ConsumoAdapter.ItemClickConsumo
{    private var id: String = ""
    private lateinit var viewModel: MesaViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos_cocina)


        viewModel = ViewModelProvider(this)[MesaViewModel::class.java]
        id = intent.getStringExtra("id") ?: ""
        viewModel.getConsumoID(id)


        val recyclePedido = findViewById<RecyclerView>(R.id.recyclerPedidos)
        val adapter= ConsumoAdapter(this)
        recyclePedido.adapter=adapter
        recyclePedido.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        val btn_Entrega=findViewById<Button>(R.id.btn_entrega)

        viewModel.listConsumoMutable?.observe(this){ pedidos->

         /*   pedidos?.let{


            }*/
            if(pedidos.isNotEmpty()) {
                adapter.setConsumo(pedidos)
                btn_Entrega.isEnabled=true
            }
            }

        btn_Entrega.setOnClickListener{
            alerta()
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


    }




    fun alerta(){
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.alerta_confrimacion, null)
        builder.setView(dialogLayout)

        val btnconf = dialogLayout.findViewById<Button>(R.id.btn_confirmar)


        val mAlertDialog = builder.show()

        btnconf.setOnClickListener {
            cambiarEstado()
            mAlertDialog.dismiss()
        }

    }
    fun cambiarEstado() {
        val db = Firebase.firestore
        val documentId = id
        val campoAActualizar = "estado"
        val nuevoValor = "Atendido"

        val referenciaDocumento = db.collection("orden").document(documentId)

        referenciaDocumento.update(campoAActualizar, nuevoValor)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
            }

    }

    override fun onItemClick(producto: ProductosFirebase) {



       /* val intent = Intent(this, PedidosActivity::class.java)
        intent.putExtra("nombre", pedidos.nombre)
        intent.putExtra("categoria", pedidos.categoria)
        intent.putExtra("imagen", pedidos.imagen)
        startActivity(intent)    */
    }
}
