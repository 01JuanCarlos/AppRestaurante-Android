package com.cibertec.apprestaurante.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Mesa::class, Categoria::class], version=1)
abstract class RestauranteRoonDatabase:RoomDatabase() {

    abstract fun mesaDao():MesaDao
    abstract fun categoriaDao(): CategoriaDAO

    companion object{
        private const val DATABASE_NAME="db_Restaurante"
        @Volatile
        private  var INSTANCE:RestauranteRoonDatabase?=null

        fun getInstance(context: Context):RestauranteRoonDatabase?{
            INSTANCE?:synchronized(this){
                INSTANCE= Room.databaseBuilder(
                    context.applicationContext,
                    RestauranteRoonDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}