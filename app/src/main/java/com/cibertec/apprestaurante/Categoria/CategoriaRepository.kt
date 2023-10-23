package com.cibertec.apprestaurante.Categoria

import android.app.Application
import androidx.lifecycle.LiveData
import com.cibertec.apprestaurante.database.Categoria
import com.cibertec.apprestaurante.database.CategoriaDAO

import com.cibertec.apprestaurante.database.RestauranteRoonDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriaRepository (application: Application) {
    private val categoriaDao: CategoriaDAO? =
        RestauranteRoonDatabase.getInstance(application)?.categoriaDao()

    suspend fun insetCategoriaWithCorovtines(categoria: Categoria) {
        processInsertCategoria(categoria)
    }

    private suspend fun processInsertCategoria(categoria: Categoria) {
        withContext(Dispatchers.Default) {
            categoriaDao?.insert(categoria)
        }
    }


    suspend fun updateCategoriaWithCoroutines(categoria: Categoria) {
        processUpdateCategoria(categoria)
    }

    private suspend fun processUpdateCategoria(categoria: Categoria) {
        withContext(Dispatchers.Default) {
            categoriaDao?.update(categoria)
        }
    }


    fun getCategoria(): LiveData<List<Categoria>>? {
        return categoriaDao?.list()
    }

}
