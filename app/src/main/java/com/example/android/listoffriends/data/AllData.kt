package com.example.android.listoffriends.data

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AllData {

    @Entity
    data class Person(val gender: String,
                      @Embedded val name: Name,
                      @Embedded val picture: Picture,
                      @NonNull @PrimaryKey val email: String,
                      @Embedded val registered: Registered) : Serializable

    data class Results(@SerializedName("results") val personList: List<Person>)
    data class Name(val first: String, val last: String)
    data class Picture(val thumbnail: String, val medium: String, val large: String)
    data class Registered(val date: String)
}