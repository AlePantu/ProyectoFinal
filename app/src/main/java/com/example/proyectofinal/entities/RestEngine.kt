package com.example.proyectofinal.entities

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestEngine {
    companion object{

        fun getRetrofitDtis(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
               // .baseUrl("https://62915b5d27f4ba1c65cb2af0.mockapi.io/")
                .baseUrl("https://6180891b8bfae60017adfb16.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit
        }
    }
}