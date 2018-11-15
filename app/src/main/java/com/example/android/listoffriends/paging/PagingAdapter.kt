package com.example.android.listoffriends.paging

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.listoffriends.R
import com.example.android.listoffriends.data.AllData
import com.example.android.listoffriends.utils.IntentAbout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*

class PagingAdapter(diffUtilCallback: DiffUtil.ItemCallback<AllData.Person>) : PagedListAdapter<AllData.Person, PagingAdapter.MyViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(person: AllData.Person?) {
            if (person != null) {
                val name = person.name.first + " " + person.name.last
                itemView.tv_name_person.text = name
                itemView.tv_gender_person.text = person.gender
                Picasso.get().load(person.picture.medium).into(itemView.image_person)
                itemView.setOnClickListener { IntentAbout.startActivity(itemView.context, person) }
            }
        }
    }
}