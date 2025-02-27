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

    private val apiServicePostman = Retrofit.Builder()
        .baseUrl("https://07dc2bea-0a78-4e18-a929-c103a5731ca5.mock.pstmn.io") // Замени на свой URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    private val apiServiceUnsplash = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    init {
        fetchMeditations()
    }

    private fun fetchMeditations() {
        viewModelScope.launch {
            try {
                val response = apiServicePostman.getMeditations()
                val meditationsWithImages = response.meditations.map { meditation ->
                    val imageResponse = apiServiceUnsplash.searchPhotos(meditation.title, "mDSnVSCQ10MtOmE35l1H-eHJFLOZOhl1qQxNE1hIgWw")
                    val imageUrl = imageResponse.results.firstOrNull()?.urls?.regular
                    meditation.copy(imageUrl = imageUrl)
                }
                _meditations.postValue(meditationsWithImages)
            } catch (e: Exception) {
                _meditations.postValue(emptyList())
            }
        }
    }
}