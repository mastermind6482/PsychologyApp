package com.example.psychologyapp.data

data class ArticleResponse(
    val articles: List<Article>
)

data class Article(
    val title: String,
    val content: String,
    val imageUrl: String? = null,
)