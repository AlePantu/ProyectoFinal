package com.example.proyectofinal.entities

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geopoint (

    var type: String,
    var coordinates: ArrayList<Double>,

        ): Parcelable {
}


