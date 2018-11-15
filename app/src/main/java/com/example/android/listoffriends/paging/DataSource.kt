package com.example.android.listoffriends.paging

import android.arch.paging.PositionalDataSource
import android.content.Context
import android.util.Log
import com.example.android.listoffriends.data.AllData
import com.example.android.listoffriends.database.MyDatabase
import com.example.android.listoffriends.network.MyRetrofit
import com.example.android.listoffriends.utils.CheckingConnection
import com.example.android.listoffriends.utils.OnShowDetails
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSource(private val context: Context, private val retrofit: MyRetrofit, val database: MyDatabase, val onShowDetails: OnShowDetails) : PositionalDataSource<AllData.Person>() {

    val personList: MutableList<AllData.Person> = mutableListOf()

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<AllData.Person>) {
        getData(params.requestedLoadSize, params.requestedStartPosition, callback, null)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<AllData.Person>) {
        getData(params.loadSize, params.startPosition, null, callback)
    }

    private fun getData(count: Int, position: Int, initialCallback: LoadInitialCallback<AllData.Person>?, rangeCallback: LoadRangeCallback<AllData.Person>?) {
        if (CheckingConnection.hasConnected(context)) {
            retrofit.apiPerson.results(count).enqueue(object : Callback<AllData.Results> {
                override fun onResponse(call: Call<AllData.Results>, response: Response<AllData.Results>) {
                    personList.addAll(response.body()!!.personList)
                    launch(CommonPool) {
                        database.daoPerson.deleteAll(database.daoPerson.getAllPersons())
                        database.daoPerson.addToDb(personList)
                    }
                    onShowDetails.hideProgress()
                    initialCallback?.onResult(personList, position)
                            ?: rangeCallback?.onResult(personList)
                }

                override fun onFailure(call: Call<AllData.Results>, t: Throwable) {
                    Log.d("mainData", "onFailure: $t")
                }
            })
        } else {
            runBlocking {
                launch(CommonPool) {
                    personList.addAll(database.daoPerson.getAllPersons())
                }.join()
            }
            if (personList.size == 0) {
                onShowDetails.showNoConnection()
            } else {
                onShowDetails.hideProgress()
                initialCallback?.onResult(personList, position)
                        ?: rangeCallback?.onResult(personList)
            }
        }
    }
}