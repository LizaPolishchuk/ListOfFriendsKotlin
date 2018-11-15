package com.example.android.listoffriends.network

import com.example.android.listoffriends.data.AllData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

 interface ApiPerson {
    @GET("api/?nat=ES")
    fun results(@Query("results") count: Int): Call<AllData.Results>
}