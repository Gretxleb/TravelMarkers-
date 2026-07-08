package ru.netology.travelmarkers.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.netology.travelmarkers.dao.MarkerDao
import ru.netology.travelmarkers.entity.MarkerEntity

@Database(entities = [MarkerEntity::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun markerDao(): MarkerDao

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    "travelmarkers.db"
                ).build().also { instance = it }
            }
    }
}
