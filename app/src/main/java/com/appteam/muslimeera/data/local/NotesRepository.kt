package com.appteam.muslimeera.data.local

import androidx.lifecycle.LiveData

class NotesRepository(private val noteDao : NotesDao) {

    val allNotes: LiveData<List<Notes>> = noteDao.getAllNotes()

    suspend fun insert(note : Notes){
        noteDao.insert(note)
    }

    suspend fun delete(note: Notes){
        noteDao.delete(note)
    }

    suspend fun update(note: Notes){
        noteDao.update(note)
    }

    fun searchByQuery(query: String): LiveData<List<Notes>> {
        return noteDao.searchByQuery(query)
    }



}