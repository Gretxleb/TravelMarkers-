package ru.netology.travelmarkers.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.netology.travelmarkers.entity.MarkerEntity

@Dao
interface MarkerDao {
    @Query("SELECT * FROM MarkerEntity ORDER BY id DESC")
    fun getAll(): LiveData<List<MarkerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(marker: MarkerEntity)

    @Query("DELETE FROM MarkerEntity WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE MarkerEntity SET title = :title, description = :description WHERE id = :id")
    suspend fun update(id: Long, title: String, description: String)
}
