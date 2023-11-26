package com.cibertec.apprestaurante.Productos


data class ProductosFirebase(
    val imagen:String,
    val nombre:String,
    val precio:Double,
    val especificacion:String,
    val descrip:String,
    val categoria:String,
    val cantidad:Int,
    val fecha: String
)
{
    constructor(nombre: String, precio: Double,imagen: String) :
            this(imagen, nombre, precio,"", "", "",0,  ""
            )

    constructor() : this("",0.0, "")
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nombre_produc" to nombre,
            "precio" to precio.toString(),
            "fecha" to fecha,
            "especif" to especificacion,
            "cantidad" to cantidad,
            "imagen" to imagen
        )
    }

    constructor(nombre: String, precio: Double,descrip: String,imagen: String) :
            this(imagen, nombre, precio,"", descrip, "",0,"")

    constructor(
        nombre: String, precio: Double, cantidad: Int, especificacion: String, fecha: String,imagen: String
    ) :
            this(imagen, nombre, precio,especificacion,"", "",cantidad,fecha)

}