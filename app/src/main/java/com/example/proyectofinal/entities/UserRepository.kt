package com.example.proyectofinal.entities

import kotlin.properties.Delegates

object UserRepository {

    public var userMailLogin : String = ""
    public var userBeachSelect : String = "0"
    public var listOfFavs : MutableList<String> = mutableListOf()
    public var ListDti = listOf<Dti>()
    public var userLatitud =""
    public var userLongitud =""
    public var ListDtiNombres : MutableList<String> = mutableListOf()




}