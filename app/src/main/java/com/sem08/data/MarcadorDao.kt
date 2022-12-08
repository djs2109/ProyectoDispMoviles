package com.sem08.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.sem08.model.Marcador

class MarcadorDao {
    //Firebase Vars
    private var codigoUsuario: String
    private var firestore:FirebaseFirestore

    init {
        codigoUsuario = Firebase.auth.currentUser?.email.toString()
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

    }

    fun getMarcadores(): MutableLiveData<List<Marcador>>{
        val listaMarcadores = MutableLiveData<List<Marcador>>()
        firestore
            .collection("marcadores")
            .document(codigoUsuario)
            .collection("misMarcadores")
            .addSnapshotListener{snapshot,e ->
                if (e!= null){
                    return@addSnapshotListener
                }
                if (snapshot != null){
                    val lista = ArrayList<Marcador>()
                    val marcadores = snapshot.documents
                    marcadores.forEach{
                        val marcador= it.toObject(Marcador::class.java)
                        if (marcador != null){
                            lista.add(marcador)
                        }
                    }
                    listaMarcadores.value = lista
                }
            }
        return listaMarcadores
    }

    fun guardarMarcador(marcador: Marcador){
        val document: DocumentReference
        if(marcador.id.isEmpty()){
            //Agregar
            document = firestore
                .collection("marcadores")
                .document(codigoUsuario)
                .collection("misMarcadores")
                .document()
            marcador.id = document.id
        }else{
            //Modificar
            document = firestore
                .collection("marcadores")
                .document(codigoUsuario)
                .collection("misMarcadores")
                .document(marcador.id)
        }
        document.set(marcador)
            .addOnCompleteListener{
                Log.d("guardarMarcador","Guardado con Exito")
            }
            .addOnCompleteListener{
                Log.e("guardarMarcador","Error al guardar")
            }
    }

    fun eliminarMarcador(marcador: Marcador){
        if (marcador.id.isNotEmpty()){
            firestore
                .collection("marcadores")
                .document(codigoUsuario)
                .collection("misMarcadores")
                .document(marcador.id)
                .delete()
                .addOnCompleteListener{
                    Log.d("eliminarMarcador","Eliminado con Exito")
                }
                .addOnCanceledListener {
                    Log.e("eliminarMarcador","Error al Eliminar")
                }
        }
    }
}