package com.example.readerscommunity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.readerscommunity.databinding.ActivityLoginBinding
import com.example.readerscommunity.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var  auth: FirebaseAuth
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()
    }

    fun login(view: View){

        if (binding.editTextEmailAddress?.text.toString() == "" || binding.editTextPassword?.text.toString() == "") {
            Toast.makeText(applicationContext, "Email or Password can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        val email = binding.editTextEmailAddress?.text.toString()
        val password = binding.editTextPassword?.text.toString()

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }


    fun goToRegister(view: View){
        val intent= Intent(this,SignUpActivity::class.java)
        startActivity(intent)
    }
}