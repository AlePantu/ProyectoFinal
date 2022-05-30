package com.example.proyectofinal.entities

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geopoint (

    var latitud : String,
    var longitud : String

        ): Parcelable {
}


