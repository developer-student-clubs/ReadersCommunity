package com.example.readerscommunity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide



class EventAdapter(private val context: Context, private val events: ArrayList<EventModel>, private val onEventClickListerner: OnEventClickListerner): RecyclerView.Adapter<EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false))
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentEvent = events[position]

        holder.eventName.text = currentEvent.eventName
        Glide.with(context).load(currentEvent.imgUrl).into(holder.eventImage)

        holder.itemView.setOnClickListener{
            onEventClickListerner.onEventItemClicked(position)


        }
    }

    override fun getItemCount(): Int {
        return events.size
    }
}