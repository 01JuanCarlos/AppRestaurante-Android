package com.cibertec.apprestaurante.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MesaDao {
    @Insert
    fun insert(mesa: Mesa)

    @Update
    fun update(mesa: Mesa)

    @Delete
    fun delete(mesa: Mesa)

    @Query("SELECT * FROM tb_mesas ORDER BY date_mesa DESC")
    fun list(): LiveData<List<Mesa>>
}