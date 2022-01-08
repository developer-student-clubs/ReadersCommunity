package com.example.readerscommunity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnEventClickListerner {

    private lateinit var auth: FirebaseAuth
    var eventList = ArrayList<EventModel>()
    var name =""
    var dduID=""
    var mobile=""
    var branch=""
    var sem=""
    var email=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title="Readers Community"
        auth = FirebaseAuth.getInstance()
        val user = Firebase.auth.currentUser
        if (user == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            generateEventList()
            loadUserInfo()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == R.id.action_logout) {
            signOut()
            return true
        }
        if (id == R.id.profile_view){
            viewProfile()
            return true
        }
        return super.onOptionsItemSelected(item)
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

    fun signOut() {
        auth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun viewProfile(){
        val intent=Intent(this,ProfileDetail::class.java)
        intent.putExtra("student_name",name)

        intent.putExtra("DDU_ID",dduID)

        intent.putExtra("Mobile_No",mobile)

        intent.putExtra("Branch",branch)

        intent.putExtra("Sem",sem)

        intent.putExtra("Email",email)

        startActivity(intent)
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
    private fun loadUserInfo() {

        var emailField = FirebaseAuth.getInstance().getCurrentUser()!!.email!!
        Log.d("id", emailField)
        FirebaseFirestore.getInstance().collection("users").whereEqualTo("Email", emailField).get().addOnCompleteListener {
                task->
            run {
                if (task.isSuccessful && task.result !== null) {
                    val userMap = task.result.documents[0].getData()

                    name = userMap!!.getValue("Name") as String
                     dduID = userMap!!.getValue("Id") as String
                     email= userMap!!.getValue("Email") as String
                     mobile = userMap!!.getValue("Mobile") as String
                     branch = userMap!!.getValue("Branch") as String
                     sem = userMap!!.getValue("Sem") as String

                }
            }
        }

    }
}