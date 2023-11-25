package com.cibertec.apprestaurante.Categoria

import com.cibertec.apprestaurante.Productos.ProductosFirebase

data class CategoriaFirestore(
    val id:String,
    val nombre:String,
    val productos:List<ProductosFirebase>
){
    constructor(id:String,nombre:String) : this(
        id=id,
        nombre=nombre,
        listOf()

    )
}
