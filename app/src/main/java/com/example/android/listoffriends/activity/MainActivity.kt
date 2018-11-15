package com.example.android.listoffriends.activity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.android.listoffriends.R
import com.example.android.listoffriends.data.AllData
import com.example.android.listoffriends.database.MyDatabase
import com.example.android.listoffriends.network.MyRetrofit
import com.example.android.listoffriends.paging.DataSourceFactory
import com.example.android.listoffriends.paging.PagingAdapter
import com.example.android.listoffriends.utils.OnShowDetails
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), OnShowDetails {

    lateinit var dataFactory: DataSourceFactory
    lateinit var adapter: PagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)

        val retrofit = MyRetrofit()
        val database = MyDatabase(this)
        dataFactory = DataSourceFactory(this, retrofit, database, this)

        val config: PagedList.Config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(5)
                .setInitialLoadSizeHint(10)
                .build()

        val pagedLiveData: LiveData<PagedList<AllData.Person>> = LivePagedListBuilder(dataFactory, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()

        adapter = PagingAdapter(object : DiffUtil.ItemCallback<AllData.Person>() {
            override fun areItemsTheSame(oldItem: AllData.Person?, newItem: AllData.Person?): Boolean {
                return oldItem!!.name == newItem!!.name
            }

            override fun areContentsTheSame(oldItem: AllData.Person?, newItem: AllData.Person?): Boolean {
                return oldItem!!.gender == newItem!!.gender
                        && oldItem.email == newItem.email
                        && oldItem.picture == newItem.picture
                        && oldItem.registered == newItem.registered
            }
        })

        pagedLiveData.observe(this, Observer<PagedList<AllData.Person>> {
            adapter.submitList(it)
        })

        recycler_view.adapter = adapter
    }

    override fun hideProgress() {
        recycler_view.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }
    override fun showNoConnection() {
        progress_bar.visibility = View.GONE
        tv_connection.visibility = View.VISIBLE
    }
}
