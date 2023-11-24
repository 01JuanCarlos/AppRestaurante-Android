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
    constructor(id: String, nombre: String, numero: Int,fecha: String) : this(
        id,
        nombre,
        numero,
        fecha, // Puedes asignar valores por defecto para otros campos si es necesario
        "",
        listOf()
    )

    constructor(id: String, consumo: List<ProductosFirebase>) : this(
        id = id,
        nombre = "",
        numero = 0,
        fecha = "",
        pago_total = "",
        consumo = consumo
    )
}