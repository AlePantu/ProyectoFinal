package com.example.proyectofinal.viewmodels

import android.app.ProgressDialog
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class RecuMailViewModel : ViewModel() {

    private lateinit var mAuth : FirebaseAuth





    fun resetPassword(emailRecu: String , v : View) {
        mAuth = FirebaseAuth.getInstance()

        mAuth.setLanguageCode("es")
        mAuth.sendPasswordResetEmail(emailRecu).addOnCompleteListener {

            if(it.isSuccessful){
                Toast.makeText( v.context , "Se ha enviado un correo para restablecer tu contraseña" ,
                Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText( v.context , "No se puedo enviar un correo para restablecer tu contraseña" ,
                    Toast.LENGTH_SHORT).show()
            }

        }
    }

}