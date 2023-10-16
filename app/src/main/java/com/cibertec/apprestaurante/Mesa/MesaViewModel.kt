package com.cibertec.apprestaurante.Mesa

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cibertec.apprestaurante.Mesa.MesaRepository
import com.cibertec.apprestaurante.database.Mesa
import kotlinx.coroutines.launch

class MesaViewModel  (application: Application): AndroidViewModel(application) {

    private val repository = MesaRepository(application)

    val mesas = repository.getMesa()

    fun saveMesaWithCoroutines(mesa: Mesa) {
        viewModelScope.launch {
            repository.insetMesaWithCorovtines(mesa)
        }
    }

    fun upateMesaWithCoroutines(mesa: Mesa) {
        viewModelScope.launch {
            repository.updateMesaWithCoroutines(mesa)
        }
    }

}