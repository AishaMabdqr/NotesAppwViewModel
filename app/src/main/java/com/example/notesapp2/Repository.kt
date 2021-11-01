package com.example.notesapp2

import androidx.lifecycle.LiveData

class Repository (private val noteDoa : NoteDao){

     val getNotes : LiveData<List<Notes>> = noteDoa.retrieveNotes()

     suspend fun addNote(note : Notes){
          noteDoa.addNote(note)
     }

     suspend fun updateNote(note : Notes){
          noteDoa.updateNote(note)
     }

     suspend fun deleteNote(note : Notes){
          noteDoa.deleteNote(note)
     }
}