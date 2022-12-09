package com.appteam.muslimeera.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.appteam.muslimeera.data.local.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(notes: Notes)

    @Update
    fun update(notes: Notes)

    @Delete
    fun delete(notes: Notes)

    @Query("SELECT * FROM noteTable order by id ASC")
    fun getAllNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM noteTable WHERE title LIKE :query")
    fun searchByQuery(query: String): LiveData<List<Notes>>


}