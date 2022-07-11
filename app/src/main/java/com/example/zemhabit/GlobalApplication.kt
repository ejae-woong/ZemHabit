package com.example.zemhabit

import android.app.Application
import androidx.room.Room
import com.example.zemhabit.data.AppDatabase

class GlobalApplication : Application() {
    companion object {
        lateinit var appInstance: GlobalApplication
            private set
        lateinit var appDataBaseInstance: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this

        appDataBaseInstance =
            Room.databaseBuilder(appInstance.applicationContext, AppDatabase::class.java, "db")
                .allowMainThreadQueries() // <- 개발시에만 적용
                .build()
    }
}