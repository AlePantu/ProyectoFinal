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

    private lateinit var listPopupWindowButton: Button
    private lateinit var listPopupWindow: ListPopupWindow

    private lateinit var listPopupCondicionButton: Button
    private lateinit var listPopupCondicion: ListPopupWindow

    private lateinit var listPopupTransporteButton: Button
    private lateinit var listPopupTransporte: ListPopupWindow

    private lateinit var listPopupFamiliaButton: Button
    private lateinit var listPopupFamilia: ListPopupWindow

    private lateinit var listPopupProvinciaButton: Button
    private lateinit var listPopupProvincia: ListPopupWindow

    private lateinit var listPopupDiasButton: Button
    private lateinit var listPopupDias: ListPopupWindow

    private lateinit var dtiSelect: TextView

    private lateinit var bSave : Button


    private  var condicionE =""
    private  var transporteE =""
    private  var familiaE =""
    private  var provinciaE =""
    private  var diasE =""
    private  var dtiSelected =""

    // private lateinit var bBack : Button




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_formulario, container, false)
        listPopupWindowButton = v.findViewById(R.id.dti_popup_button)
        listPopupWindow =
            ListPopupWindow(requireContext(), null, androidx.transition.R.attr.listPopupWindowStyle)

        listPopupCondicionButton = v.findViewById(R.id.popCondicion)
        listPopupCondicion=
            ListPopupWindow(requireContext(), null, androidx.transition.R.attr.listPopupWindowStyle)

        listPopupTransporteButton = v.findViewById(R.id.popTransporte)
        listPopupTransporte =
            ListPopupWindow(requireContext(), null, androidx.transition.R.attr.listPopupWindowStyle)

        listPopupFamiliaButton = v.findViewById(R.id.popFamilia)
        listPopupFamilia =
            ListPopupWindow(requireContext(), null, androidx.transition.R.attr.listPopupWindowStyle)

        listPopupProvinciaButton = v.findViewById(R.id.popProvincia)
        listPopupProvincia =
            ListPopupWindow(requireContext(), null, androidx.transition.R.attr.listPopupWindowStyle)

        listPopupDiasButton = v.findViewById(R.id.popDias)
        listPopupDias =
            ListPopupWindow(requireContext(), null, androidx.transition.R.attr.listPopupWindowStyle)

        dtiSelect = v.findViewById(R.id.dtiForm)

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

        ///////////////////////////////////////////////////////
        listPopupCondicion.anchorView = listPopupCondicionButton

        val adapterCondicion =
            ArrayAdapter(requireContext(), R.layout.list_popup_window_item, condicion)
        listPopupCondicion.setAdapter(adapterCondicion)

// Set list popup's item click listener
        listPopupCondicion.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

            condicionE = condicion[position]
            listPopupCondicionButton.text = condicionE

            // Dismiss popup.
            listPopupCondicion.dismiss()
        }

// Show list popup window on button click.
        listPopupCondicionButton.setOnClickListener { listPopupCondicion.show() }




        ///////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////
        listPopupTransporte.anchorView = listPopupTransporteButton

        val adapterTransporte =
            ArrayAdapter(requireContext(), R.layout.list_popup_window_item, transporte)
        listPopupTransporte.setAdapter(adapterTransporte)

// Set list popup's item click listener
        listPopupTransporte.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

            transporteE = transporte[position]
            listPopupTransporteButton.text = transporteE

            // Dismiss popup.
            listPopupTransporte.dismiss()
        }

// Show list popup window on button click.
        listPopupTransporteButton.setOnClickListener { listPopupTransporte.show() }




        ///////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////
        listPopupFamilia.anchorView = listPopupFamiliaButton

        val adapterFamilia =
            ArrayAdapter(requireContext(), R.layout.list_popup_window_item, familia)
        listPopupFamilia.setAdapter(adapterFamilia)

// Set list popup's item click listener
        listPopupFamilia.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

            familiaE = familia[position]
            listPopupFamiliaButton.text = familiaE

            // Dismiss popup.
            listPopupFamilia.dismiss()
        }

// Show list popup window on button click.
        listPopupFamiliaButton.setOnClickListener { listPopupFamilia.show() }




        ///////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////
        listPopupProvincia.anchorView = listPopupProvinciaButton

        val adapterProvincia =
            ArrayAdapter(requireContext(), R.layout.list_popup_window_item, provincia)
        listPopupProvincia.setAdapter(adapterProvincia)

// Set list popup's item click listener
        listPopupProvincia.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

            provinciaE = provincia[position]
            listPopupProvinciaButton.text = provinciaE

            // Dismiss popup.
            listPopupProvincia.dismiss()
        }

// Show list popup window on button click.
        listPopupProvinciaButton.setOnClickListener { listPopupProvincia.show() }




        ///////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////
        listPopupDias.anchorView = listPopupDiasButton

        val adapterDias =
            ArrayAdapter(requireContext(), R.layout.list_popup_window_item, dias)
        listPopupDias.setAdapter(adapterDias)

// Set list popup's item click listener
        listPopupDias.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

            diasE = dias[position]
            listPopupDiasButton.text = diasE

            // Dismiss popup.
            listPopupDias.dismiss()
        }

// Show list popup window on button click.
        listPopupDiasButton.setOnClickListener { listPopupDias.show() }




        ///////////////////////////////////////////////////////

        bSave.setOnClickListener {

            if (formOk()){
                vm.saveForm(v, dtiSelected ,condicionE, transporteE , familiaE , provinciaE , diasE)
                Toast.makeText(requireContext(),"Formulario enviado - Gracias", Toast.LENGTH_SHORT).show()
                activity?.onBackPressed()

            } else {
                Toast.makeText(requireContext(),"Complete todos los campos del formulario , Gracias", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun formOk(): Boolean {

            var ok = false
        if(dtiSelected.isNotBlank() && condicionE.isNotBlank() && transporteE.isNotBlank()
            && familiaE.isNotBlank()  && provinciaE.isNotBlank()  && diasE.isNotBlank() ){

            ok = true
        }

        return ok


    }
}

