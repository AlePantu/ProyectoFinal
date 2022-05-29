package com.example.proyectofinal.viewmodels

import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.google.firebase.firestore.FirebaseFirestore

class FormularioViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var formTransporte: TextView
    private lateinit var formGrupoFamiliar: TextView
    private lateinit var formResidencia: TextView
    private lateinit var formEstadia: TextView

    fun saveForm(v: View , dti : String) : Boolean{
        val ok = false
        formTransporte = v.findViewById(R.id.editTransporte)
        formGrupoFamiliar = v.findViewById(R.id.editGrupoFamiliar)
        formResidencia = v.findViewById(R.id.editLoc)
        formEstadia = v.findViewById(R.id.editEstadia)

        val transporte = formTransporte.text.toString()
        val gFamiliar = formGrupoFamiliar.text.toString()
        val resi = formResidencia.text.toString()
        val estadia = formEstadia.text.toString()
        //userMailLogin
        //dti

        return ok
    }
}