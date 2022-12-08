package com.appteam.muslimeera.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Notes::class], version = 2, exportSchema = false)
abstract class NotesDB : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var instance: NotesDB? = null

        fun getDatabase(context: Context): NotesDB {
            if (instance == null) {
                synchronized(NotesDB::class.java) {
                    instance = Room.databaseBuilder(context, NotesDB::class.java, "notes.db")
                        .fallbackToDestructiveMigration().build()
                }
            }
            return instance as NotesDB
        }
    }
}