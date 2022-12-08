package com.sem08.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.sem08.data.MarcadorDao
import com.sem08.model.Lugar
import com.sem08.repository.MarcadorRepository

class HomeViewModel (application: Application): AndroidViewModel(application) {
    private val repository: MarcadorRepository = MarcadorRepository(MarcadorDao())
    val obtenerMarcadores: MutableLiveData<List<Lugar>>

    init{
        obtenerMarcadores = repository.obtenerMarcadores
    }

    fun guardarMarcador(lugar: Lugar){
        repository.guardarMarcador(lugar)
    }
    fun eliminarMarcador(lugar: Lugar){
        repository.eliminarMarcador(lugar)
    }
}