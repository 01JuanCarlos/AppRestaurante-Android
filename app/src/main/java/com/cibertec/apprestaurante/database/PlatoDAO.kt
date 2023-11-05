package com.cibertec.apprestaurante.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlatoDAO {

    @Insert
    fun insert(plato: Plato)

    @Update
    fun update(plato: Plato)

    @Delete
    fun delete(plato: Plato)

    @Query("SELECT * FROM tb_plato ORDER BY id_plato ASC")
    fun list(): LiveData<List<Plato>>
}