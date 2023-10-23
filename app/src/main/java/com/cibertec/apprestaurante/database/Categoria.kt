package com.cibertec.apprestaurante.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="tb_categoria")

data class Categoria(
    @ColumnInfo("nombre_categoria")
    val nombre:String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id_categoria")
    var id_categoria:Int=0
}


