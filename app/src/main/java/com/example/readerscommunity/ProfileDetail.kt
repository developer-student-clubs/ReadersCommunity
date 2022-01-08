package com.example.readerscommunity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.activity_view_profile.*

class ProfileDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)

        val Student_name = intent.getStringExtra("student_name")
        val College_ID = intent.getStringExtra("DDU_ID")
        val Mobile_No = intent.getStringExtra("Mobile_No")
        val Branch = intent.getStringExtra("Branch")
        val Sem = intent.getStringExtra("Sem")
        val Email = intent.getStringExtra("Email")

        profile_name.text = Student_name
        profile_email.text = Email
        detail_name_edit.text = Student_name
        detail_branch_edit.text = Branch
        detail_mobile_edit.text = Mobile_No
        detail_id_edit.text = College_ID
        detail_sem_edit.text = Sem

        //Glide.with(this).load(eventImg).into(event_image)

    }
}