package com.example.readerscommunity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_event_detail.*

class EventDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        val eventTitle = intent.getStringExtra("name")
        val eventImg = intent.getStringExtra("image")
        val eventDate = intent.getStringExtra("date")
        val eventDes = intent.getStringExtra("des")
        val eventTiming = intent.getStringExtra("time")
        val eventVenue = intent.getStringExtra("venue")
        val eventBranch = intent.getStringExtra("branch")
        val eventSem = intent.getStringExtra("sem")
        val eventWhatToBring = intent.getStringExtra("what_to_bring")
        val eventExtra = intent.getStringExtra("extra")

        event_title_detail.text = eventTitle
        event_date.text = eventDate
        event_des.text = eventDes
        event_time.text = eventTiming
        event_venue.text = eventVenue
        event_branch.text = eventBranch
        event_sem.text = eventSem
        event_what_to_bring.text = eventWhatToBring
        event_extra.text = eventExtra

        Glide.with(this).load(eventImg).into(event_image)

    }
}