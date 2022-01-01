package com.example.readerscommunity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val eventName: TextView = itemView.findViewById(R.id.event_title)
    val eventImage: ImageView = itemView.findViewById(R.id.event_img)

}