package com.example.android.listoffriends.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.android.listoffriends.data.AllData

@Dao
interface DaoPerson {
    @Insert
    fun addToDb(personList: List<AllData.Person>)
    @Delete
    fun deleteAll(personList: List<AllData.Person>)

    @Query("SELECT*FROM person")
    fun getAllPersons(): List<AllData.Person>
}