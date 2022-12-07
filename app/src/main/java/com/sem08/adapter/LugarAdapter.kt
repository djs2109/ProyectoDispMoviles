package com.sem08.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sem08.databinding.MarcadorFilaBinding
import com.sem08.model.Lugar
import com.sem08.ui.home.HomeFragmentDirections
import com.sem08.ui.home.UpdateMarcadorFragmentDirections

class LugarAdapter: RecyclerView.Adapter<LugarAdapter.LugarViewHolder>() {

    //Lista de Lugares
    private var listaLugares = emptyList<Lugar>()

    fun setLugares(lugares: List<Lugar>){
        listaLugares = lugares
        notifyDataSetChanged()
    }

    inner class LugarViewHolder(private val itemBinding: MarcadorFilaBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(lugar: Lugar){
            itemBinding.tvEquipo1.text = lugar.equipo1
            itemBinding.tvMarcador1.text = lugar.marcador1
            itemBinding.tvEquipo2.text = lugar.equipo2
            itemBinding.tvMarcador2.text = lugar.marcador2
            //Evento enviar Update
            itemBinding.vistaFila.setOnClickListener{
                val accion = HomeFragmentDirections.actionNavHomeToUpdateLugarFragment(lugar)
                itemView.findNavController().navigate(accion)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {
        val itemBinding = MarcadorFilaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LugarViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
        val lugar = listaLugares[position]
        holder.dibuja(lugar)
    }

    override fun getItemCount(): Int {
        return listaLugares.size
    }


}