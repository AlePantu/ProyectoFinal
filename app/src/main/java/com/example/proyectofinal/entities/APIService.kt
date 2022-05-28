package com.example.proyectofinal.entities

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET("dtis")
    fun getDtiList(): Call<List<Dti>>

    @GET
    fun getDtiByid(@Url url: String): Call<Dti>

}