package com.demopract

import android.app.Application
import androidx.room.Room
import com.demopract.roomDB.UserInfoDatabase
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.stetho.Stetho

class MyApplicationClass : Application(){
    companion object{
        var database : UserInfoDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        Fresco.initialize(this)
        database = Room.databaseBuilder(applicationContext,UserInfoDatabase::class.java,"user_db").fallbackToDestructiveMigration().build()
    }
}