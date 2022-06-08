package com.example.proyectofinal.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class AddressDti(
    var streetAddress: String,
    var addressRegion: String,
    var addressLocality: String,
    var postalCode: String
) : Parcelable
