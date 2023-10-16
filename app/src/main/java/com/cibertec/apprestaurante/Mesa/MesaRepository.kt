package com.cibertec.apprestaurante.Mesa

import android.app.Application
import androidx.lifecycle.LiveData
import com.cibertec.apprestaurante.database.Mesa
import com.cibertec.apprestaurante.database.MesaDao
import com.cibertec.apprestaurante.database.RestauranteRoonDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MesaRepository (application: Application) {
    private val mesaDao:MesaDao?=
        RestauranteRoonDatabase.getInstance(application)?.mesaDao()

    suspend fun insetMesaWithCorovtines(mesa: Mesa){
        processInsertMesa(mesa)
    }
    private  suspend fun processInsertMesa(mesa:Mesa){
        withContext(Dispatchers.Default){
            mesaDao?.insert(mesa)
        }
    }



    suspend fun updateMesaWithCoroutines(mesa: Mesa) {
        processUpdateMesa(mesa)
    }

    private suspend fun processUpdateMesa(mesa: Mesa) {
        withContext(Dispatchers.Default) {
            mesaDao?.update(mesa)
        }
    }


    fun getMesa(): LiveData<List<Mesa>>?{
            return mesaDao?.list()

}
}