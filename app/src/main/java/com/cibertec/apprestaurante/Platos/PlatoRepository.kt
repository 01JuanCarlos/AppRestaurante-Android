package com.cibertec.apprestaurante.Plato

import android.app.Application
import androidx.lifecycle.LiveData
import com.cibertec.apprestaurante.database.Plato
import com.cibertec.apprestaurante.database.PlatoDAO
import com.cibertec.apprestaurante.database.RestauranteRoonDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlatoRepository (application: Application) {
    private val platoDao: PlatoDAO? =
        RestauranteRoonDatabase.getInstance(application)?.platodao()

    suspend fun insetPlatoWithCorovtines(plato: Plato) {
        processInsertPlato(plato)
    }

    private suspend fun processInsertPlato(plato: Plato) {
        withContext(Dispatchers.Default) {
            platoDao?.insert(plato)
        }
    }
    suspend fun updatePlatoWithCoroutines(plato: Plato) {
        processUpdatePlato(plato)
    }

    private suspend fun processUpdatePlato(plato: Plato) {
        withContext(Dispatchers.Default) {
            platoDao?.update(plato)
        }
    }


    fun getPlato(): LiveData<List<Plato>>? {
        return platoDao?.list()
    }

}

