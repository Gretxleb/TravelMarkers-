package ru.netology.travelmarkers.repository

import androidx.lifecycle.LiveData
import ru.netology.travelmarkers.dto.Marker

interface MarkerRepository {
    val data: LiveData<List<Marker>>
    suspend fun save(marker: Marker)
    suspend fun deleteById(id: Long)
    suspend fun update(id: Long, title: String, description: String)
}
