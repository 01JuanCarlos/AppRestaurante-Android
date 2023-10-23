package com.cibertec.apprestaurante.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CategoriaDAO {
    @Insert
    fun insert(categoria: Categoria)

    @Update
    fun update(categoria: Categoria)

    @Delete
    fun delete(categoria: Categoria)

    @Query("SELECT * FROM tb_categoria ORDER BY id_categoria DESC")
    fun list(): LiveData<List<Categoria>>
}
