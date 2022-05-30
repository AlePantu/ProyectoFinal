package com.example.proyectofinal.viewmodels

import android.app.AlertDialog
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.entities.Favoritos
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class LoginViewModel : ViewModel() {

    var db = FirebaseFirestore.getInstance()

    fun registerOK(v : View) {

        db.collection("users").document(userMailLogin).set(
            hashMapOf(
                "favs" to mutableListOf<Favoritos>()
            ))

        Snackbar.make(v, "Registro exitoso, inicie sesion", Snackbar.LENGTH_SHORT).show()
    }
    fun registerFail(v : View){



        Snackbar.make(v, "Error: se ha producido un error registrando al usuaio", Snackbar.LENGTH_SHORT).show()
    }

    fun loginFail(v : View){


        Snackbar.make(v, "USUARIO or PASSWORD incorrecto", Snackbar.LENGTH_SHORT).show()



    }
}