package com.example.readerscommunity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.readerscommunity.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_view_profile.*

class ProfileActivity : AppCompatActivity() {
    // view binding
    private lateinit var binding: ActivityProfileBinding

    //firebase auth

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        //loadUserInfo()

        //handle click, go back

//        backBtn.setOnClickListener {
//            onBackPressed()
//        }
//
//        //handle click, edit
//
//        editBtn.setOnClickListener{
//
//        }

    }

    private fun loadUserInfo() {

        val db= Firebase.firestore.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnCompleteListener {
            task->
            run {
                if (task.isSuccessful && task.result !== null) {
                    //eventList = task.toObjects(EventModel::class.java) as ArrayList<EventModel>
                    val name = task.result.getString("Name")
                    val dduID = task.result.getString("Id")
                    val Mobile = task.result.getString("Mobile")
                    val Branch = task.result.getString("Branch")
                    val Sem = task.result.getString("Sem")

                }
            }
        }

    }


    }