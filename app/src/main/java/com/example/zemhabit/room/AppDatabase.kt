package com.example.zemhabit.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.zemhabit.data.HabitData

@Database(entities = arrayOf(HabitData::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract  fun HabitDataDao(): HabitDataDao
}