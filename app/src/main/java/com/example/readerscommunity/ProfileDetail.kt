package com.example.readerscommunity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.readerscommunity.databinding.ActivityCreateProfileBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.activity_view_profile.*

class ProfileDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)

        //handle click, go back

        backBtn.setOnClickListener {
            onBackPressed()
        }

        val Student_name = intent.getStringExtra("student_name")
        val College_ID = intent.getStringExtra("DDU_ID")
        val Mobile_No = intent.getStringExtra("Mobile_No")
        val Branch = intent.getStringExtra("Branch")
        val Sem = intent.getStringExtra("Sem")
        val Email = intent.getStringExtra("Email")

        Log.d("id", "@@@@@@@@@@@@@@@@")
        Log.d("id", Student_name!!)
        Log.d("id", College_ID!!)
        profile_name.text = Student_name
        profile_email.text = Email
        detail_name_edit.text = Student_name
        detail_branch_edit.text = Branch
        detail_mobile_edit.text = Mobile_No
        detail_id_edit.text = College_ID
        detail_sem_edit.text = Sem


    }
}
