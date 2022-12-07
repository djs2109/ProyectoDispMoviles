package com.sem08.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lugar(

    var id: String,
    val equipo1: String,
    val marcador1: String?,
    val equipo2: String,
    val marcador2: String? //el simbolo de pregunta permite nulos
) : Parcelable{
    constructor():
            this("","","","","")
}
