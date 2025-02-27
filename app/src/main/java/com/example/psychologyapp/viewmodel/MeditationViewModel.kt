package com.example.psychologyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psychologyapp.data.ApiService
import com.example.psychologyapp.data.Meditation
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MeditationViewModel : ViewModel() {
    private val _meditations = MutableLiveData<List<Meditation>>()
    val meditations: LiveData<List<Meditation>> get() = _meditations

    private val apiService = Retrofit.Builder()
        .baseUrl("https://your-postman-mock-server-id.mock.pstmn.io/") // Замени на свой URL от Postman
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    init {
        fetchMeditations()
    }

    private fun fetchMeditations() {
        viewModelScope.launch {
            try {
                val response = apiService.getMeditations()
                _meditations.postValue(response.meditations)
            } catch (e: Exception) {
                _meditations.postValue(emptyList())
            }
        }
    }
}