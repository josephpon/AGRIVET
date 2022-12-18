package com.example.agrivet

import android.app.Notification
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.agrivet.databinding.ActivityClientHomePageBinding
import com.example.agrivet.databinding.ActivityHomePageBinding

class ClientHomePage : AppCompatActivity() {

    private lateinit var binding: ActivityClientHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(ClientHome())

        binding.bottomNavigationView.setOnItemSelectedListener{

            when(it.itemId){

                R.id.vethomepage -> replaceFragment(ClientHome())
                R.id.vetprofile -> replaceFragment(ClientProfile())

                else ->{

                }
            }
            true
        }
    }
    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}