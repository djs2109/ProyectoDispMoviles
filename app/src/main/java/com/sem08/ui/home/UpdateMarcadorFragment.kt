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
        binding.etEquipo1.setText(args.lugarArg.equipo1)
        binding.etMarcador1.setText(args.lugarArg.marcador1)
        binding.etEquipo2.setText(args.lugarArg.equipo2)
        binding.etMarcador2.setText(args.lugarArg.marcador2)

        binding.btUpdateLugar.setOnClickListener{ updateLugar()}
        binding.btDeleteLugar.setOnClickListener{ deleteLugar()}

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun updateLugar(){
        val nombre = binding.etEquipo1.text.toString()
        val correo = binding.etMarcador1.text.toString()
        val telefono = binding.etEquipo2.text.toString()
        val web = binding.etMarcador2.text.toString()

        if(nombre.isNotEmpty()){
            val lugar = Lugar(args.lugarArg.id,nombre,correo,telefono,web)
            homeViewModel.guardarLugar(lugar)
            Toast.makeText(requireContext(),getString(R.string.ms_UpdateLugar),Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateLugarFragment_to_nav_home)
        }else{
            Toast.makeText(requireContext(),getString(R.string.ms_FaltaValores),Toast.LENGTH_LONG).show()
        }

    }

    private fun deleteLugar(){
        val nombre = binding.etEquipo1.text.toString()
        val telefono = binding.etMarcador1.text.toString()
        val correo = binding.etEquipo2.text.toString()
        val web = binding.etMarcador2.text.toString()

        val lugar = Lugar(args.lugarArg.id,nombre,correo,web,telefono)
        homeViewModel.eliminarLugar(lugar)
        Toast.makeText(requireContext(),getString(R.string.ms_DeleteLugar),Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_updateLugarFragment_to_nav_home)
    }
}