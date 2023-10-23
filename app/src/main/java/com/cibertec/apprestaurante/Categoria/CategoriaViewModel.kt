package com.cibertec.apprestaurante.Categoria

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cibertec.apprestaurante.database.Categoria
import com.cibertec.apprestaurante.database.Mesa
import kotlinx.coroutines.launch

class CategoriaViewModel (application: Application): AndroidViewModel(application) {

    private val repository = CategoriaRepository(application)

    val categoria = repository.getCategoria()
    fun saveCategoriaWithCoroutines(categoria: Categoria) {
        viewModelScope.launch {
            repository.insetCategoriaWithCorovtines(categoria)
        }
    }

    fun upateCategoriaWithCoroutines(categoria: Categoria) {
        viewModelScope.launch {
            repository.updateCategoriaWithCoroutines(categoria)
        }
    }
}