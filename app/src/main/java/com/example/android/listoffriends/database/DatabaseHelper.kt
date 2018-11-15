package com.example.android.listoffriends.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.android.listoffriends.data.AllData

@Database(entities = [AllData.Person::class], version = 1, exportSchema = false)
abstract class DatabaseHelper : RoomDatabase(){
    abstract fun getDaoPerson(): DaoPerson
}