package com.example.agrivet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vetlogInBtn: Button= findViewById(R.id.veterinarian)
        val clientlogInBtn: Button= findViewById(R.id.button2)

        vetlogInBtn.setOnClickListener(){
            val intent = Intent(this,VetLogIn::class.java)
            startActivity(intent)
        }

        clientlogInBtn.setOnClickListener(){
            val intent = Intent(this,ClientLogIn::class.java)
            startActivity(intent)
        }


    }
}