package com.example.readerscommunity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.inflate
import android.widget.Button
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.readerscommunity.databinding.ActivityCreateProfileBinding.inflate
import com.example.readerscommunity.databinding.ActivityEventDetailBinding
import com.google.firebase.firestore.FirebaseFirestore

class EventDetail : AppCompatActivity() {

    lateinit var binding: ActivityEventDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        binding.eventTitleDetail.text = eventTitle
        binding.eventDate.text = eventDate
        binding.eventDes.text = eventDes
        binding.eventTime.text = eventTiming
        binding.eventVenue.text = eventVenue
        binding.eventBranch.text = eventBranch
        binding.eventSem.text = eventSem
        binding.eventWhatToBring.text = eventWhatToBring
        binding.eventExtra.text = eventExtra

        Glide.with(this).load(eventImg).into(binding.eventImage)

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