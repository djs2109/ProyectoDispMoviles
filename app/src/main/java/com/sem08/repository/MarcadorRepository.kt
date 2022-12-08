package com.sem08.repository

import androidx.lifecycle.MutableLiveData
import com.sem08.data.MarcadorDao
import com.sem08.model.Marcador

class MarcadorRepository (private val marcadorDao: MarcadorDao){
    fun guardarMarcador(marcador: Marcador){
        marcadorDao.guardarMarcador(marcador)
    }

    fun eliminarMarcador(marcador: Marcador){
        marcadorDao.eliminarMarcador(marcador)
    }

    val obtenerMarcadores: MutableLiveData<List<Marcador>> = marcadorDao.getMarcadores()
}