package com.example.notesapp2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.*


@Dao
interface NoteDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote (note : Notes)

    @Query("SELECT * FROM notes ORDER BY pk ASC")
     fun retrieveNotes() : LiveData<List<Notes>>

    @Update
    suspend fun updateNote(note : Notes)

    @Delete
    suspend fun deleteNote(note : Notes)
}