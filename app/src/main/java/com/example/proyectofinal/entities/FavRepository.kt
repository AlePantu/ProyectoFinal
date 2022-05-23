package com.example.proyectofinal.entities

import com.example.proyectofinal.entities.UserRepository.listOfFavs
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.google.firebase.firestore.FirebaseFirestore

private val db = FirebaseFirestore.getInstance()


class FavRepository {

  var favList: MutableList<Favoritos> = mutableListOf()

    init {

        db.collection("users").document(userMailLogin).get().addOnSuccessListener {

            listOfFavs = it.get("favs") as ArrayList<String>
        }
    }
}