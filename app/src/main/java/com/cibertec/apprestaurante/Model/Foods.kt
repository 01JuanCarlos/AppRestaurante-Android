package com.cibertec.apprestaurante.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Foods(
    val image: Int,
    val title: String,
    val price: String,
    val description: String
)
