package com.example.readerscommunity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.readerscommunity.databinding.ActivityCreateProfileBinding
import com.example.readerscommunity.databinding.ActivityProfileBinding
import com.example.readerscommunity.databinding.ActivityViewProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileDetail : AppCompatActivity() {

    lateinit var binding: ActivityViewProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //handle click, go back

        binding.backBtn.setOnClickListener {
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
        binding.profileName.text = Student_name
        binding.profileEmail.text = Email
        binding.detailNameEdit.text = Student_name
        binding.detailBranchEdit.text = Branch
        binding.detailMobileEdit.text = Mobile_No
        binding.detailIdEdit.text = College_ID
        binding.detailSemEdit.text = Sem


    }
}
