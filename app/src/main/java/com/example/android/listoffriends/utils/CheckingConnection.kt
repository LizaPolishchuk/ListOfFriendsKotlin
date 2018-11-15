package com.example.android.listoffriends.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class CheckingConnection{
    companion object {
        fun hasConnected(context: Context): Boolean{
            val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: NetworkInfo? = manager.activeNetworkInfo
            return networkInfo?.isConnected ?: false
        }
    }
}