package com.example.psychologyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psychologyapp.data.ApiService
import com.example.psychologyapp.data.Photo
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchViewModel : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> get() = _photos

    private val apiService = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    fun searchImages(query: String) {
        viewModelScope.launch {
            try {
                val response = apiService.searchPhotos(query, "YOUR_UNSPLASH_API_KEY") // Замени на свой ключ
                _photos.postValue(response.results)
            } catch (e: Exception) {
                _photos.postValue(emptyList())
            }
        }
    }
}