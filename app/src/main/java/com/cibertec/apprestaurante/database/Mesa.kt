package com.cibertec.apprestaurante.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="tb_mesas")
data class Mesa(
    @ColumnInfo("numero_mesa")
    val numero:Number,
    @ColumnInfo("nombre_mesa")
    val nombre:String,

    @ColumnInfo("date_mesa")
    val date:String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id_mesa")
    var Id_mesa:Int=0
}
