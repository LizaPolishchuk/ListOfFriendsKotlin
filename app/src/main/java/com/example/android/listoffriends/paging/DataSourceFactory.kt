package com.example.android.listoffriends.paging

import android.content.Context
import com.example.android.listoffriends.data.AllData
import com.example.android.listoffriends.database.MyDatabase
import com.example.android.listoffriends.network.MyRetrofit
import com.example.android.listoffriends.utils.OnShowDetails

class DataSourceFactory(private val context: Context, private val retrofit: MyRetrofit, private val database: MyDatabase, private val onShowDetails: OnShowDetails) : android.arch.paging.DataSource.Factory<Int, AllData.Person>() {
    override fun create(): android.arch.paging.DataSource<Int, AllData.Person> {
        return com.example.android.listoffriends.paging.DataSource(context, retrofit, database, onShowDetails)
    }
}