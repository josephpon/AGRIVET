package com.example.agrivet

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.agrivet.databinding.ActivityClientSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ClientSignUp : AppCompatActivity() {

    private lateinit var binding: ActivityClientSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid
        database = FirebaseDatabase.getInstance().getReference("Client")
        binding.clientSignUp.setOnClickListener {

            val email = binding.clientEmail.text.toString()
            val pass = binding.clientPassword.text.toString()
            val clientName = binding.cName.text.toString()
            val clientAge = binding.cAge.text.toString()
            val clientAddress = binding.cAddress.text.toString()
            val  clientGender= binding.cGender.text.toString()
            val clientContactNum = binding.cContactNum.text.toString()


            val client = Client(clientName,clientAge,clientAddress,clientGender,clientContactNum,email,)

            if(uid !=null) {
                database.child(uid).setValue(client).addOnCompleteListener {

                    if(it.isSuccessful){

                        uploadProfilePic()
                    }else{

                        Toast.makeText(this@ClientSignUp,"Failed to update profile",Toast.LENGTH_SHORT).show()
                    }



                    Toast.makeText(this, "Successfully Save !!", Toast.LENGTH_SHORT).show()
                }

            }

            val clientSignUpBtn: Button = findViewById(R.id.clientLogIn)

            clientSignUpBtn.setOnClickListener(){
                val intent = Intent(this,ClientLogIn::class.java)
                startActivity(intent)
            }


            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, ClientLogIn::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(this, "Something Is Missing !!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun uploadProfilePic() {

        imageUri = Uri.parse("android.resource://$packageName/${R.drawable.avatar}")
            storageReference = FirebaseStorage.getInstance().getReference("Client/"+firebaseAuth.currentUser?.uid+".jpg")
        storageReference.putFile(imageUri).addOnSuccessListener {

            Toast.makeText(this@ClientSignUp,"Profile Successfully Upload",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{

            Toast.makeText(this@ClientSignUp,"Failed to update Image",Toast.LENGTH_SHORT).show()
        }


    }
}