package com.example.proyectofinal.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Favoritos (

    var name : String,
    var id : Int,
    var temp : Float,
    var aforo : Int

        ) : Parcelable{

        }