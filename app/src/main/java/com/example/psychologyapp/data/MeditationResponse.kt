package com.example.psychologyapp.data

data class MeditationResponse(
    val meditations: List<Meditation>
)

data class Meditation(
    val title: String,
    val duration: Long,
    val imageUrl: String? = null
)