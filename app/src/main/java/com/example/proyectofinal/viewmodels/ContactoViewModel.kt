package com.example.proyectofinal.viewmodels

import androidx.lifecycle.ViewModel
import com.example.proyectofinal.entities.UserRepository

class ContactoViewModel : ViewModel() {

    fun cleanLogUser() {
        UserRepository.userMailLogin = ""
    }
    // TODO: Implement the ViewModel
}