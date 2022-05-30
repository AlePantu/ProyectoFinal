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
import com.example.proyectofinal.entities.UserRepository
import com.example.proyectofinal.entities.UserRepository.ListDti
import com.example.proyectofinal.viewmodels.FormularioViewModel

class FormularioFragment : Fragment() {

    private lateinit var v: View

    private val vm: FormularioViewModel by viewModels()

    private lateinit var listPopupWindowButton: Button
    private lateinit var listPopupWindow: ListPopupWindow

    private lateinit var dtiSelect: TextView

    private lateinit var bSave : Button


   // private lateinit var bBack : Button

    private var ListDtiNombres = mutableListOf<String>()

    private lateinit var dtiSelected : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_formulario, container, false)
        listPopupWindowButton = v.findViewById(R.id.dti_popup_button)
        listPopupWindow =
            ListPopupWindow(requireContext(), null, androidx.transition.R.attr.listPopupWindowStyle)
        dtiSelect = v.findViewById(R.id.dtiForm)
        getDtiNames(ListDti)
        bSave = v.findViewById(R.id.btnGuardar)

       // bBack = v.findViewById(R.id.btnAtras)

        return v
    }

    override fun onStart() {
        super.onStart()

        listPopupWindow.anchorView = listPopupWindowButton

        val adapter =
            ArrayAdapter(requireContext(), R.layout.list_popup_window_item, ListDtiNombres)
        listPopupWindow.setAdapter(adapter)

// Set list popup's item click listener
        listPopupWindow.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

            dtiSelected = ListDtiNombres[position]
            dtiSelect.text = dtiSelected

            // Dismiss popup.
            listPopupWindow.dismiss()
        }

// Show list popup window on button click.
        listPopupWindowButton.setOnClickListener { listPopupWindow.show() }

      /*  bBack.setOnClickListener {
            activity?.onBackPressed()
        }*/

        bSave.setOnClickListener {
            //Activar formulario
           // vm.saveForm(v, dtiSelected)
            Toast.makeText(requireContext(),"Formulario enviado - Gracias", Toast.LENGTH_SHORT).show()
            activity?.onBackPressed()
        }

    }


    fun getDtiNames(list: List<Dti>): MutableList<String> {

        for (l in list) {
            ListDtiNombres.addAll(listOf(l.nombre))
        }
        return ListDtiNombres
    }
}

