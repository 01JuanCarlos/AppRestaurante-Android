package com.cibertec.apprestaurante.Mesa

import com.cibertec.apprestaurante.Productos.ProductosFirebase

data class MesaFirebase(
    val id:String,
    val nombre: String,
    val numero: Int,
    val fecha: String,
    val pago_total: String,
    val consumo: List<ProductosFirebase>
) {
    constructor(id: String, nombre: String, numero: Int,fecha: String,pago_total: String) : this(
        id,
        nombre,
        numero,
        fecha,
        pago_total,
        listOf()
    )


    constructor(id: String,nombre: String,numero: Int, consumo: List<ProductosFirebase>,pago_total: String) : this(
        id = id,
        nombre = nombre,
        numero = numero,
        fecha = "",
        pago_total = pago_total,
        consumo = consumo
    )



}