package com.example.notesapp2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Notes::class], version = 1, exportSchema =  false)
abstract class NoteDB : RoomDatabase() {

    abstract fun notesDao() : NoteDao

    companion object{

        @Volatile
        private var INSTANCE : NoteDB? = null // we create this variable so we can only have one instance of this db

        fun getDatabase(context: Context) : NoteDB{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder( context.applicationContext, NoteDB::class.java, "notes").fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }

        }





    }
}