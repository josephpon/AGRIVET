package com.example.agrivet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.agrivet.databinding.ActivityVetSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.PhantomReference

class VetSignUp : AppCompatActivity() {

    private lateinit var binding:ActivityVetSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVetSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        val vetlogInBtn: Button= findViewById(R.id.vetlogin)

        vetlogInBtn.setOnClickListener(){
            val intent = Intent(this,VetLogIn::class.java)
            startActivity(intent)
        }

            binding.vetsignup.setOnClickListener{

            val email = binding.emailET.text.toString()
            val pass = binding.passET.text.toString()
                val name = binding.VTName.text.toString()
                val degree = binding.VTDegree.text.toString()
                val school = binding.VTSchool.text.toString()
                val  clinic= binding.VTClinic.text.toString()
                val contact = binding.VTContact.text.toString()
                val gmeet = binding.VTGLink.text.toString()

                database = FirebaseDatabase.getInstance().getReference("Veterinarian")
                val veterinarian = Veterinarian(name,degree,school,clinic,contact,email,gmeet)
                database.child(name).setValue(veterinarian).addOnSuccessListener {

                    binding.VTName.text?.clear()
                    binding.VTDegree.text?.clear()
                    binding.VTSchool.text?.clear()
                    binding.VTClinic.text?.clear()
                    binding.VTContact.text?.clear()
                    binding.VTGLink.text?.clear()

                    Toast.makeText(this, "Successfully Save !!", Toast.LENGTH_SHORT).show()
                }
            if (email.isNotEmpty() && pass.isNotEmpty()){

                firebaseAuth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent( this, VetLogIn::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            } else{
                Toast.makeText(this, "Something Is Missing !!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}