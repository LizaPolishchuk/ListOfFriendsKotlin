package com.example.android.listoffriends.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyRetrofit{
        private val baseUrl: String = "https://randomuser.me/"
        private val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val apiPerson: ApiPerson = retrofit.create(ApiPerson::class.java)
}