package com.example.proyectofinal.viewmodels

import android.content.Context
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.FormRepository
import com.example.proyectofinal.entities.UserRepository
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.google.firebase.firestore.FirebaseFirestore

class FormularioViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

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


    private var condicionE = ""
    private var transporteE = ""
    private var familiaE = ""
    private var provinciaE = ""
    private var diasE = ""
    private var dtiSelected = ""



    fun showDti(v: View, context: Context){
        dtiSelect = v.findViewById(R.id.dtiForm)
        listPopupWindowButton = v.findViewById(R.id.dti_popup_button)
        listPopupWindow =
            ListPopupWindow(context, null, androidx.transition.R.attr.listPopupWindowStyle)

        listPopupWindow.anchorView = listPopupWindowButton

        val adapter =
            ArrayAdapter(
                context,
                R.layout.list_popup_window_item,
                UserRepository.ListDtiNombres
            )
        listPopupWindow.setAdapter(adapter)

        // Set list popup's item click listener
        listPopupWindow.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

            dtiSelected = UserRepository.ListDtiNombres[position]
            dtiSelect.text = dtiSelected

            // Dismiss popup.
            listPopupWindow.dismiss()
        }

        // Show list popup window on button click.
        listPopupWindowButton.setOnClickListener { listPopupWindow.show() }
    }

    fun showCondicion(v: View, context: Context){
        listPopupCondicionButton = v.findViewById(R.id.popCondicion)
        listPopupCondicion =
            ListPopupWindow(context, null, androidx.transition.R.attr.listPopupWindowStyle)

        listPopupCondicion.anchorView = listPopupCondicionButton

        val adapterCondicion =
            ArrayAdapter(context, R.layout.list_popup_window_item, FormRepository.condicion)
        listPopupCondicion.setAdapter(adapterCondicion)

        // Set list popup's item click listener
        listPopupCondicion.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

            condicionE = FormRepository.condicion[position]
            listPopupCondicionButton.text = condicionE

            // Dismiss popup.
            listPopupCondicion.dismiss()
        }

        // Show list popup window on button click.
        listPopupCondicionButton.setOnClickListener { listPopupCondicion.show() }

    }

    fun showTransporte(v: View, context: Context){
        listPopupTransporteButton = v.findViewById(R.id.popTransporte)
        listPopupTransporte =
            ListPopupWindow(context, null, androidx.transition.R.attr.listPopupWindowStyle)

        listPopupTransporte.anchorView = listPopupTransporteButton

        val adapterTransporte =
            ArrayAdapter(context, R.layout.list_popup_window_item,
                FormRepository.transporte
            )
        listPopupTransporte.setAdapter(adapterTransporte)

        // Set list popup's item click listener
        listPopupTransporte.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

            transporteE = FormRepository.transporte[position]
            listPopupTransporteButton.text = transporteE

            // Dismiss popup.
            listPopupTransporte.dismiss()
        }

        // Show list popup window on button click.
        listPopupTransporteButton.setOnClickListener { listPopupTransporte.show() }
    }

    fun showFamilia(v: View, context: Context){
        listPopupFamiliaButton = v.findViewById(R.id.popFamilia)
        listPopupFamilia =
            ListPopupWindow(context, null, androidx.transition.R.attr.listPopupWindowStyle)

        listPopupFamilia.anchorView = listPopupFamiliaButton

        val adapterFamilia =
            ArrayAdapter(context, R.layout.list_popup_window_item, FormRepository.familia)

        listPopupFamilia.setAdapter(adapterFamilia)

        // Set list popup's item click listener
        listPopupFamilia.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

            familiaE = FormRepository.familia[position]
            listPopupFamiliaButton.text = familiaE

            // Dismiss popup.
            listPopupFamilia.dismiss()
        }

        // Show list popup window on button click.
        listPopupFamiliaButton.setOnClickListener { listPopupFamilia.show() }
    }

    fun showProvincia(v: View, context: Context){
        listPopupProvinciaButton = v.findViewById(R.id.popProvincia)
        listPopupProvincia =
            ListPopupWindow(context, null, androidx.transition.R.attr.listPopupWindowStyle)

        listPopupProvincia.anchorView = listPopupProvinciaButton

        val adapterProvincia =
            ArrayAdapter(context, R.layout.list_popup_window_item,
                FormRepository.provincia
            )
        listPopupProvincia.setAdapter(adapterProvincia)

        // Set list popup's item click listener
        listPopupProvincia.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

            provinciaE = FormRepository.provincia[position]
            listPopupProvinciaButton.text = provinciaE

            // Dismiss popup.
            listPopupProvincia.dismiss()
        }

        // Show list popup window on button click.
        listPopupProvinciaButton.setOnClickListener { listPopupProvincia.show() }
    }

    fun showDias(v: View, context: Context){
        listPopupDiasButton = v.findViewById(R.id.popDias)
        listPopupDias =
            ListPopupWindow(context, null, androidx.transition.R.attr.listPopupWindowStyle)

        listPopupDias.anchorView = listPopupDiasButton

        val adapterDias =
            ArrayAdapter(context, R.layout.list_popup_window_item, FormRepository.dias)
        listPopupDias.setAdapter(adapterDias)

        // Set list popup's item click listener
        listPopupDias.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

            diasE = FormRepository.dias[position]
            listPopupDiasButton.text = diasE

            // Dismiss popup.
            listPopupDias.dismiss()
        }

        // Show list popup window on button click.
        listPopupDiasButton.setOnClickListener { listPopupDias.show() }
    }

    fun saveForm() : Boolean{
        val ok = false

        db.collection("forms").document().set(
            hashMapOf(
                "dti" to dtiSelected,
                "condicion" to condicionE,
                "transporte" to transporteE,
                "familia" to familiaE,
                "provincia" to provinciaE ,
                "dias" to diasE
            )
        )
        return ok
    }

    fun formOk(): Boolean {

        var ok = false
        if(dtiSelected.isNotBlank() && condicionE.isNotBlank() && transporteE.isNotBlank()
            && familiaE.isNotBlank()  && provinciaE.isNotBlank()  && diasE.isNotBlank() ){

            ok = true
        }

        return ok


    }

    fun messageSaveFormOk(context: Context){
        Toast.makeText(context, "Formulario enviado - Gracias", Toast.LENGTH_SHORT)
            .show()
    }

    fun messageSaveFormFailed(context: Context){
        Toast.makeText(context, "Complete todos los campos del formulario, Gracias", Toast.LENGTH_SHORT)
            .show()
    }
}