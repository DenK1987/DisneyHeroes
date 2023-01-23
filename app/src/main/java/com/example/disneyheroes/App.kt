package com.example.disneyheroes

import android.app.Application
import com.example.disneyheroes.db.DataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DataBase.initDB(applicationContext)
    }
}