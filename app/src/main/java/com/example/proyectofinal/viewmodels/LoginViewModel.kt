package com.example.proyectofinal.viewmodels

import android.app.AlertDialog
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar

class LoginViewModel : ViewModel() {

    fun registerOK(v : View) {
        Snackbar.make(v, "Registro exitoso, inicie sesion", Snackbar.LENGTH_SHORT).show()
    }
    fun registerFail(v : View){
        Snackbar.make(v, "Error:" + "se ha producido un error registrando al usuaio", Snackbar.LENGTH_SHORT).show()
    }

    fun loginFail(v : View){


        Snackbar.make(v, "USUARIO or PASSWORD incorrecto", Snackbar.LENGTH_SHORT).show()



    }
}