package com.sem08.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sem08.data.LugarDao
import com.sem08.model.Lugar

class LugarRepository (private val lugarDao: LugarDao){
    fun guardarMarcador(marcador: Lugar){
        lugarDao.guardarMarcador(marcador)
    }

    fun eliminarMarcador(marcador: Lugar){
        lugarDao.eliminarMarcador(marcador)
    }

    val obtenerMarcadores: MutableLiveData<List<Lugar>> = lugarDao.getMarcadores()
}