package com.cibertec.apprestaurante.Productos

import com.cibertec.apprestaurante.database.Categoria




data class ProductosFirebase(
    val imagen:String,
    val nombre:String,
    val precio:String,
    val descrip:String,
    val categoria:String
) {
    constructor(nombre: String, precio: String) :
            this("", nombre, precio, "", "")
}