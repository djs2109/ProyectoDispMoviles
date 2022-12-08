package com.sem08.repository

import androidx.lifecycle.MutableLiveData
import com.sem08.data.MarcadorDao
import com.sem08.model.Lugar

class MarcadorRepository (private val marcadorDao: MarcadorDao){
    fun guardarMarcador(marcador: Lugar){
        marcadorDao.guardarMarcador(marcador)
    }

    fun eliminarMarcador(marcador: Lugar){
        marcadorDao.eliminarMarcador(marcador)
    }

    val obtenerMarcadores: MutableLiveData<List<Lugar>> = marcadorDao.getMarcadores()
}