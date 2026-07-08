package ru.netology.travelmarkers.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.travelmarkers.dto.Marker

@Entity
data class MarkerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val lat: Double,
    val lng: Double
) {
    fun toDto() = Marker(id, title, description, lat, lng)

    companion object {
        fun fromDto(dto: Marker) = MarkerEntity(dto.id, dto.title, dto.description, dto.lat, dto.lng)
    }
}
