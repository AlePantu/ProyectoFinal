package com.example.proyectofinal.viewmodels

import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.google.firebase.firestore.FirebaseFirestore

class FormularioViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

 fun saveForm(v: View , dti : String , condicion : String, transporte : String, familia : String,
              provincia : String, dias : String) : Boolean{
        val ok = false

     db.collection("forms").document().set(
         hashMapOf(
             "dti" to dti,
             "condicion" to condicion,
             "transporte" to transporte,
             "familia" to familia,
             "provincia" to provincia ,
             "dias" to dias
         )
     )
        return ok
    }
}