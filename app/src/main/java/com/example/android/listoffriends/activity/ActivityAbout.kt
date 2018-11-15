package com.example.android.listoffriends.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.listoffriends.data.AllData
import com.example.android.listoffriends.utils.IntentAbout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_about.*

class ActivityAbout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val person: AllData.Person = intent.getSerializableExtra(IntentAbout.SERIALIZABLE_PERSON) as AllData.Person
        val name = "${person.name.first} ${person.name.last}"

        tv_about_name.text = name
        tv_about_gender.text = person.gender
        tv_about_emile.text = person.email
        tv_about_date.text = person.registered.date
        Picasso.get().load(person.picture.large).into(image_about)
    }
}