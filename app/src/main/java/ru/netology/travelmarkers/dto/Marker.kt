package ru.netology.travelmarkers.dto

data class Marker(
    val id: Long = 0,
    val title: String,
    val description: String,
    val lat: Double,
    val lng: Double
)
