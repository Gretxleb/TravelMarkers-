package ru.netology.travelmarkers.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.netology.travelmarkers.db.AppDb
import ru.netology.travelmarkers.dto.Marker
import ru.netology.travelmarkers.repository.MarkerRepositoryImpl

class MarkerViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MarkerRepositoryImpl(
        AppDb.getInstance(application).markerDao()
    )

    val data = repository.data

    fun save(marker: Marker) = viewModelScope.launch {
        repository.save(marker)
    }

    fun deleteById(id: Long) = viewModelScope.launch {
        repository.deleteById(id)
    }

    fun update(id: Long, title: String, description: String) = viewModelScope.launch {
        repository.update(id, title, description)
    }
}
