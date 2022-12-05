package com.appteam.muslimeera.data.local

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {
    val allNotes : LiveData<List<Notes>>
    val repository: NotesRepository

    init {
        val dao = NotesDB.getDatabase(application).getNotesDao()
        repository = NotesRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(notes: Notes) = viewModelScope.launch (Dispatchers.IO) {
        repository.delete(notes)
    }

    fun updateNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(notes)
    }

    fun addNote(notes: Notes) = viewModelScope.launch (Dispatchers.IO) {
        repository.insert(notes)
    }

}