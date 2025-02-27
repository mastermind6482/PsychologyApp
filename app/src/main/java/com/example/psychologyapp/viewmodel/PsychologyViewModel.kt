package com.example.psychologyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psychologyapp.data.ApiService
import com.example.psychologyapp.data.Article
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PsychologyViewModel : ViewModel() {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    private val _notes = MutableLiveData<String>("")
    val notes: LiveData<String> get() = _notes

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
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            try {
                val response = apiServicePostman.getArticles()
                val articlesWithImages = response.articles.map { article ->
                    val imageResponse = apiServiceUnsplash.searchPhotos(article.title, "mDSnVSCQ10MtOmE35l1H-eHJFLOZOhl1qQxNE1hIgWw")
                    val imageUrl = imageResponse.results.firstOrNull()?.urls?.regular
                    article.copy(imageUrl = imageUrl)
                }
                _articles.postValue(articlesWithImages)
            } catch (e: Exception) {
                _articles.postValue(emptyList())
            }
        }
    }

    fun updateNotes(newNotes: String) {
        _notes.value = newNotes
    }
}