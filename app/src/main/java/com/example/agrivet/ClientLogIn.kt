package com.example.agrivet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.agrivet.databinding.ActivityClintLogInBinding
import com.example.agrivet.databinding.ActivityVetLogInBinding
import com.google.firebase.auth.FirebaseAuth

class ClientLogIn : AppCompatActivity() {

    private lateinit var binding: ActivityClintLogInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityClintLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.clientSignUp.setOnClickListener() {
            val intent = Intent(this, ClientSignUp::class.java)
            startActivity(intent)
        }

        binding.clientLogIn.setOnClickListener {
            val email = binding.clientEmailLG.text.toString()
            val pass = binding.clientPasswordLG.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {

                    if (it.isSuccessful) {
                        val intent = Intent(this, ClientHomePage::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are Not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}