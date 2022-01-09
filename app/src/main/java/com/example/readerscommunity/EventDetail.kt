package com.example.readerscommunity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
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
        val id = intent.getStringExtra("id")
        val seats = intent.getIntExtra("currentSeatAvailable", 0)
        val register = findViewById<Button>(R.id.eventRegister)

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

        register.setOnClickListener{
            if (seats == 0)
                Toast.makeText(applicationContext,"Seats for the event are full!!", Toast.LENGTH_LONG).show()
            else{
                if (id != null) {
                    register(id, seats)
                    Toast.makeText(
                        applicationContext,
                        "Registration Successful!!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
    }
}

  fun register(id: String, seats: Int){

      if (id != null){
          var ref = FirebaseFirestore.getInstance().collection("events").document(id.trim());

          ref.update("currentSeatAvailable", seats-1)

          var dduid = user.dduID
          if (dduid != null) {
              ref.collection("participants").document(dduid).set(user)
          }
      }
  }