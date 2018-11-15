package com.example.android.listoffriends.database

import android.arch.persistence.room.Room
import android.content.Context

class MyDatabase(context: Context){
    private val database: DatabaseHelper = Room.databaseBuilder(context, DatabaseHelper::class.java, "personsDb")
            .build()

    val daoPerson: DaoPerson = database.getDaoPerson()
}