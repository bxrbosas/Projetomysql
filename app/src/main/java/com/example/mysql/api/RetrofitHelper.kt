package com.example.mysql.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    //private const val BASE_URL = "http://10.86.141.2/api/"
    private const val BASE_URL = "http://10.143.13.52/api/"

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }
}