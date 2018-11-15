package com.example.android.listoffriends.utils

import android.content.Context
import android.content.Intent
import com.example.android.listoffriends.activity.ActivityAbout
import com.example.android.listoffriends.data.AllData

class IntentAbout {

    companion object {
        const val SERIALIZABLE_PERSON = "person"

        fun startActivity(context: Context, person: AllData.Person) {
            val intent = Intent(context, ActivityAbout::class.java)
            intent.putExtra(SERIALIZABLE_PERSON, person)
            context.startActivity(intent)
        }
    }
}