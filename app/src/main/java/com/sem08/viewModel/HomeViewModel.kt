package com.sem08.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.sem08.data.MarcadorDao
import com.sem08.model.Marcador
import com.sem08.repository.MarcadorRepository

class HomeViewModel (application: Application): AndroidViewModel(application) {
    private val repository: MarcadorRepository = MarcadorRepository(MarcadorDao())
    val obtenerMarcadores: MutableLiveData<List<Marcador>>

    init{
        obtenerMarcadores = repository.obtenerMarcadores
    }

    fun guardarMarcador(marcador: Marcador){
        repository.guardarMarcador(marcador)
    }
    fun eliminarMarcador(marcador: Marcador){
        repository.eliminarMarcador(marcador)
    }
}