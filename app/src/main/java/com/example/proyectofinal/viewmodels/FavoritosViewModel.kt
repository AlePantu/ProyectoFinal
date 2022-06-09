package com.example.proyectofinal.viewmodels

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.entities.FavRepository
import com.example.proyectofinal.entities.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FavoritosViewModel : ViewModel() {
    fun cleanLogUser() {
        UserRepository.userMailLogin = ""
    }


    var repository : FavRepository = FavRepository()
    var favoritos = repository.favList
    lateinit var recyclerFavs: RecyclerView
     var db = FirebaseFirestore.getInstance()

    fun dialog(context: Context, activity : Activity) {
        AlertDialog.Builder(context)
            .setMessage("Cerrar Aplicacion?")
            .setCancelable(false)
            .setPositiveButton("Aceptar") { dialog, whichButton ->
                FirebaseAuth.getInstance().signOut()
                cleanLogUser()
                activity.finish()
            }
            .setNegativeButton("Cancelar") { dialog, whichButton ->

            }
            .show()

    }


}