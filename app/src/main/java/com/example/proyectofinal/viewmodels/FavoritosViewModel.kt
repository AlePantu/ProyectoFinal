package com.example.proyectofinal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.entities.FavRepository
import com.example.proyectofinal.entities.UserRepository
import com.google.firebase.firestore.FirebaseFirestore

class FavoritosViewModel : ViewModel() {


    var repository : FavRepository = FavRepository()
    var favoritos = repository.favList
    lateinit var recyclerFavs: RecyclerView
     var db = FirebaseFirestore.getInstance()


}