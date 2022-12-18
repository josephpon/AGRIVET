package com.example.agrivet.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.agrivet.Repository.VeterinarianRepository

class VeterinarianViewModel: ViewModel() {

    private val repository : VeterinarianRepository
    private val _allUsers = MutableLiveData<List<Veterinarian>>()
    val allUsers : LiveData<List<Veterinarian>> = _allUsers


    init{

        repository = VeterinarianRepository().getInstance()
        repository.loadUsers(_allUsers)
    }
}