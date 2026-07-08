package ru.netology.travelmarkers.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.netology.travelmarkers.dao.MarkerDao
import ru.netology.travelmarkers.dto.Marker
import ru.netology.travelmarkers.entity.MarkerEntity

class MarkerRepositoryImpl(private val dao: MarkerDao) : MarkerRepository {
    override val data: LiveData<List<Marker>> = dao.getAll().map { list ->
        list.map(MarkerEntity::toDto)
    }

    override suspend fun save(marker: Marker) {
        dao.insert(MarkerEntity.fromDto(marker))
    }

    override suspend fun deleteById(id: Long) {
        dao.deleteById(id)
    }

    override suspend fun update(id: Long, title: String, description: String) {
        dao.update(id, title, description)
    }
}
