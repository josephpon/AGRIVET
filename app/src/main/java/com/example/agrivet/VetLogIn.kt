package com.example.agrivet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import com.example.agrivet.databinding.ActivityVetLogInBinding
import com.google.firebase.auth.FirebaseAuth

class VetLogIn : AppCompatActivity() {

    private lateinit var binding: ActivityVetLogInBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVetLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUp.setOnClickListener() {
            val intent = Intent(this, VetSignUp::class.java)
            startActivity(intent)
        }

        binding.logIn.setOnClickListener {
            val email = binding.emailET1.text.toString()
            val pass = binding.passET1.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {

                    if (it.isSuccessful) {
                        val intent = Intent(this, HomePage::class.java)
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

      //  override fun onStart() {
      //  super.onStart()

       //  if(firebaseAuth.currentUser != null){

       //       val intent = Intent(this,VetLogIn::class.java)
       //      startActivity(intent)
       //  }
    }

//}

//it.exception.toString()