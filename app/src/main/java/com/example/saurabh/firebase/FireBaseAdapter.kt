package com.example.saurabh.firebase

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image.view.*


data class uploaded(val url:String)

class FireBaseAdapter(val data:ArrayList<uploaded> ): RecyclerView.Adapter<FireBaseAdapter.FirebaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirebaseViewHolder {
        val li=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val holder = li.inflate(R.layout.image,parent,false)
        return FirebaseViewHolder(holder)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: FirebaseViewHolder, position: Int) {
        Picasso.get().load(data[position].url).resize(500,500).into(holder.itemView.ivRetrieved)
    }


    class FirebaseViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {

    }
}