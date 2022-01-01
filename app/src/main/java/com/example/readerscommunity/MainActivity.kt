package com.example.readerscommunity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnEventClickListerner {

    private lateinit var auth: FirebaseAuth
    var eventList = ArrayList<EventModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        title="Welcome"
//        auth = FirebaseAuth.getInstance()
//        val user = Firebase.auth.currentUser
//        if (user == null) {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        } else {

            generateEventList()

//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
    }

    fun generateEventList() {
        FirebaseFirestore.getInstance()
            .collection("events")
            .get()
            .addOnSuccessListener { documents ->
                for(document in documents) {
                    eventList = documents.toObjects(EventModel::class.java) as ArrayList<EventModel>
                    var eventAdapter = EventAdapter(this, eventList, this)
                    rv_event_list.layoutManager = LinearLayoutManager(this)
                    rv_event_list.adapter = eventAdapter

                }

                eventList.sortByDescending { it.postedOn }

            }.addOnFailureListener{

            }
    }

    fun signOut(view: View) {
        auth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onEventItemClicked(position: Int) {
        val intent = Intent(this, EventDetail::class.java)
        intent.putExtra("name", eventList[position].eventName)
        intent.putExtra("image", eventList[position].imgUrl)
        intent.putExtra("date", eventList[position].date)
        intent.putExtra("des", eventList[position].description)
        intent.putExtra("time", eventList[position].timings)
        intent.putExtra("venue", eventList[position].venue)
        intent.putExtra("branch", eventList[position].branch)
        intent.putExtra("sem", eventList[position].semester)
        intent.putExtra("what_to_bring", eventList[position].what_to_bring)
        intent.putExtra("extra", eventList[position].extraInfo)
        startActivity(intent)
    }
}