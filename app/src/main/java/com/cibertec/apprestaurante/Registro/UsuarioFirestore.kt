package com.cibertec.apprestaurante.Registro

data class UsuarioFirestore(
    val nombre: String,
    val apellido: String,
    val correo: String,
    val contraseña: String,
    val tipo: String
)
