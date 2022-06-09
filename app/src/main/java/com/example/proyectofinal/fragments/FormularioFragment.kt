package com.example.proyectofinal.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.Dti
import com.example.proyectofinal.entities.FormRepository.condicion
import com.example.proyectofinal.entities.FormRepository.dias
import com.example.proyectofinal.entities.FormRepository.familia
import com.example.proyectofinal.entities.FormRepository.provincia
import com.example.proyectofinal.entities.FormRepository.transporte
import com.example.proyectofinal.entities.UserRepository
import com.example.proyectofinal.entities.UserRepository.ListDti
import com.example.proyectofinal.entities.UserRepository.ListDtiNombres
import com.example.proyectofinal.viewmodels.FormularioViewModel

class FormularioFragment : Fragment() {

    private lateinit var v: View

    private val vm: FormularioViewModel by viewModels()

    private lateinit var bSave : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_formulario, container, false)

        bSave = v.findViewById(R.id.btnGuardar)


        return v
    }

    override fun onStart() {
        super.onStart()

        vm.showDti(v, requireContext())
        vm.showCondicion(v, requireContext())
        vm.showTransporte(v, requireContext())
        vm.showFamilia(v, requireContext())
        vm.showProvincia(v, requireContext())
        vm.showDias(v, requireContext())

        bSave.setOnClickListener {

            if (vm.formOk()) {
                vm.saveForm()
                vm.messageSaveFormOk(requireContext())
                activity?.onBackPressed()

            } else {
                vm.messageSaveFormFailed(requireContext())
            }


        }
    }


}

