package com.sem08.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sem08.databinding.MarcadorFilaBinding
import com.sem08.model.Lugar
import com.sem08.ui.home.HomeFragmentDirections

class MarcadorAdapter: RecyclerView.Adapter<MarcadorAdapter.MarcadorViewHolder>() {

    //Lista de Marcadores
    private var listaMarcadores = emptyList<Lugar>()

    fun setMarcadores(marcadores: List<Lugar>){
        listaMarcadores = marcadores
        notifyDataSetChanged()
    }

    inner class MarcadorViewHolder(private val itemBinding: MarcadorFilaBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(marcador: Lugar){
            itemBinding.tvEquipo1.text = marcador.equipo1
            itemBinding.tvMarcador1.text = marcador.marcador1
            itemBinding.tvEquipo2.text = marcador.equipo2
            itemBinding.tvMarcador2.text = marcador.marcador2
            //Evento enviar Update
            itemBinding.vistaFila.setOnClickListener{
                val accion = HomeFragmentDirections.actionNavHomeToUpdateMarcadorFragment(marcador)
                itemView.findNavController().navigate(accion)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarcadorViewHolder {
        val itemBinding = MarcadorFilaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MarcadorViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MarcadorViewHolder, position: Int) {
        val marcador = listaMarcadores[position]
        holder.dibuja(marcador)
    }

    override fun getItemCount(): Int {
        return listaMarcadores.size
    }


}