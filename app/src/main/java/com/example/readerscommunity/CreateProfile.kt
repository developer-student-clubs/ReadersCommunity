package com.example.readerscommunity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.readerscommunity.databinding.ActivityCreateProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateProfile : AppCompatActivity() {
    lateinit var binding: ActivityCreateProfileBinding
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)
        binding = ActivityCreateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun saveProfile (view: View) {
        val studentId = binding.editStudentId.text.toString()
        val name = binding.editName.text.toString()
        val branch = binding.editBranch.text.toString()
        val sem = binding.editSem.text.toString()
        val mobile = binding.editMobile.text.toString()
        val email = FirebaseAuth.getInstance().currentUser?.email

        val newUser = hashMapOf(
            "Id" to studentId,
            "Email" to email,
            "Name" to name,
            "Mobile" to mobile,
            "Branch" to branch,
            "Sem" to sem
        )
        db.collection("users").document(studentId).set(newUser).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
}