package com.cibertec.apprestaurante.Plato

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cibertec.apprestaurante.database.Plato
import kotlinx.coroutines.launch

class PlatoViewModel  (application: Application): AndroidViewModel(application) {
    private val repository = PlatoRepository(application)

    val plato = repository.getPlato()
    fun savePlatoWithCoroutines(plato : Plato) {
        viewModelScope.launch {
            repository.insetPlatoWithCorovtines(plato)
        }
    }

    fun upatePlatoWithCoroutines(plato: Plato) {
        viewModelScope.launch {
            repository.updatePlatoWithCoroutines(plato)
        }
    }
}
