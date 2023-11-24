package com.cibertec.apprestaurante.Productos





data class ProductosFirebase(
    val imagen:String,
    val nombre:String,
    val precio:String,
    val descrip:String,
    val categoria:String
) {
    constructor(nombre: String, precio: String) :
            this("", nombre, precio, "", "")
    constructor() : this("", "")
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nombre_produc" to nombre,
            "precio" to precio
            // Agrega otros campos si es necesario
        )
    }
}