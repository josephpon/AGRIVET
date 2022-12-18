package com.example.agrivet.Repository

import androidx.lifecycle.MutableLiveData
import com.example.agrivet.Models.Veterinarian
import com.google.firebase.database.*

class VeterinarianRepository {

    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Veterinarian")

    @Volatile private var INSTANCE : VeterinarianRepository ?= null

    fun getInstance() : VeterinarianRepository{

        return INSTANCE ?: synchronized(this){

            val instance = VeterinarianRepository()
            INSTANCE = instance
            instance
        }

    }

    fun loadUsers(userList: MutableLiveData<List<Veterinarian>>) {

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                try{

                    val _userList : List<Veterinarian> = snapshot.children.map{ dataSnapshot ->  

                        dataSnapshot.getValue(Veterinarian::class.java)!!
                    }

                    userList.postValue(_userList)

                }catch (e : Exception){

                }



            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }


}