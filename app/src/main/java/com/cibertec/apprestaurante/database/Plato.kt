package com.cibertec.apprestaurante.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

    @Entity(tableName="tb_plato")

        data class Plato(
        @ColumnInfo("nombre")
        val nombre: String,
        @ColumnInfo("categoria")
        val categoria:String,
        @ColumnInfo("descripcion")
        val descripcion:String,
        @ColumnInfo("precio")
        val precio:Double

){
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo("id_plato")
        var id_plato:Int=0
}

