package com.example.proyectofinal.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Dti (

    var id : String,
    var type: String,
    var TimeInstant: String,
    var address: AddressDti,
    var aforo : String,
    var altOla : Float,
    var bandera : String,
    var brandName: String,
    var category: String,
    var controlledProperty: ArrayList<String>,
    var dirViento : String,
    var function: String,
    var location: Geopoint,
    var manufacturerName: String,
    var maxParking : Float,
    var modelName: String,
    var name : String,
    var parking: Float,
    var playa: String,
    var refPlaya: String,
    var t: String,
    var temperatura: Float,
    var velViento : String,
    var uv : String,


) : Parcelable {

}
