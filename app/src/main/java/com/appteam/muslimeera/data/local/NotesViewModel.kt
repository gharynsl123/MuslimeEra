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
        val dao = NotesDB.getDatabase(application).notesDao()
        repository = NotesRepository(dao)
        allNotes = repository.allNotes
    }

    //funsi ini yang akang di gunakan oleh view ketika input data dan dikirimkan ke dalam repository
    fun insertData(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(notes)
        }
    }

    fun searchByQuery(query: String): LiveData<List<Notes>> = repository.searchByQuery(query)

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