package com.appteam.muslimeera.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appteam.muslimeera.data.local.Notes


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