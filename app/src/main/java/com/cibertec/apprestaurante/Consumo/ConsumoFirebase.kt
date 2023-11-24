package com.cibertec.apprestaurante.Consumo

import com.cibertec.apprestaurante.Productos.ProductosFirebase
import java.time.format.DateTimeFormatter

data class ConsumoFirebase(
    val consumo:Array<ProductosFirebase>,
    val fecha:DateTimeFormatter,
    val mesa:Int,
    val nombre:String,
    val pago_total:Double
)
