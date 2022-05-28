package com.example.proyectofinal.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Dti (

    var nombre : String,
    var id : Int,
    var geopoint : String ,
    var aforo : String ,
    var altOla : String ,
    var bandera : String ,
    var dirViento : String ,
    var maxAforo : String ,
    var maxPark : String ,
    var parking : String ,
    var temperatura : String ,
    var velViento : String ,
    var uv : String,


) : Parcelable {

}
