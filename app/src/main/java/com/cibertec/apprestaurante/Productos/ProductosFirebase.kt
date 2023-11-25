package com.cibertec.apprestaurante.Productos

import java.time.LocalDateTime

data class ProductosFirebase(
    val imagen:String,
    val nombre:String,
    val precio:String,
    val especificacion:String,
    val descrip:String,
    val categoria:String,
    val cantidad:Int,
    val fecha: String
)
{
    constructor(nombre: String, precio: String) :
            this("", nombre, precio,"", "", "",0,  ""
            )
    constructor() : this("", "")
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nombre_produc" to nombre,
            "precio" to precio,
            "fecha" to fecha,
            "especif" to especificacion,
            "cantidad" to cantidad
            // Agrega otros campos si es necesario
        )
    }
    constructor(nombre: String, precio: String,descrip: String) :
            this("", nombre, precio,"", descrip, "",0,"")


    constructor(
        nombre: String, precio: String, cantidad: Int, especificacion: String, fecha: String
    ) :
            this("", nombre, precio,especificacion,"", "",cantidad,fecha)

}