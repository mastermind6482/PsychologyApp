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

    private val apiService = Retrofit.Builder()
        .baseUrl("https://your-postman-mock-server-id.mock.pstmn.io/") // Замени на свой URL от Postman
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            try {
                val response = apiService.getArticles()
                _articles.postValue(response.articles)
            } catch (e: Exception) {
                _articles.postValue(emptyList())
            }
        }
    }

    fun updateNotes(newNotes: String) {
        _notes.value = newNotes
    }
}