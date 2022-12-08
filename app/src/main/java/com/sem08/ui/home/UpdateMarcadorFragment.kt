package com.sem08.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sem08.R
import com.sem08.databinding.FragmentUpdateMarcadorBinding
import com.sem08.model.Lugar
import com.sem08.viewModel.HomeViewModel

class UpdateMarcadorFragment : Fragment() {

    //Recuperar los elementos enviados
    private val args by navArgs<UpdateMarcadorFragmentArgs>()

    private var _binding: FragmentUpdateMarcadorBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentUpdateMarcadorBinding.inflate(inflater,container,false)

        //Carga de Lugar
        binding.etEquipo1.setText(args.marcadorArg.equipo1)
        binding.etMarcador1.setText(args.marcadorArg.marcador1)
        binding.etEquipo2.setText(args.marcadorArg.equipo2)
        binding.etMarcador2.setText(args.marcadorArg.marcador2)

        binding.btUpdateMarcador.setOnClickListener{ updateMarcador()}
        binding.btDeleteMarcador.setOnClickListener{ deleteMarcador()}

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun updateMarcador(){
        val equipo1 = binding.etEquipo1.text.toString()
        val marcador1 = binding.etMarcador1.text.toString()
        val equipo2 = binding.etEquipo2.text.toString()
        val marcador2 = binding.etMarcador2.text.toString()

        if(equipo1.isNotEmpty()){
            val marcador = Lugar(args.marcadorArg.id,equipo1,marcador1,equipo2,marcador2)
            homeViewModel.guardarMarcador(marcador)
            Toast.makeText(requireContext(),getString(R.string.ms_UpdateLugar),Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateMarcadorFragment_to_nav_home)
        }else{
            Toast.makeText(requireContext(),getString(R.string.ms_FaltaValores),Toast.LENGTH_LONG).show()
        }

    }

    private fun deleteMarcador(){
        val equipo1 = binding.etEquipo1.text.toString()
        val marcador1 = binding.etMarcador1.text.toString()
        val equipo2 = binding.etEquipo2.text.toString()
        val marcador2 = binding.etMarcador2.text.toString()

        val marcador = Lugar(args.marcadorArg.id,equipo1,marcador1,equipo2,marcador2)
        homeViewModel.eliminarLugar(marcador)
        Toast.makeText(requireContext(),getString(R.string.ms_DeleteLugar),Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_updateMarcadorFragment_to_nav_home)
    }
}