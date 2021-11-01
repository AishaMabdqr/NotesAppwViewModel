package com.example.notesapp2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application : Application) :AndroidViewModel(application) {

    private val repository : Repository
    private val notes : LiveData<List<Notes>>

    init {
        val noteDao = NoteDB.getDatabase(application).notesDao()
        repository = Repository(noteDao)
        notes = repository.getNotes
    }


    fun getNotes() : LiveData<List<Notes>>{
        return notes
    }

    fun addNote (noteText : String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addNote(Notes(0,noteText))
        }
    }

    fun deleteNote (pk : Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteNote(Notes(pk,""))
        }
    }

    fun updateNote (pk: Int, noteText : String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateNote(Notes(pk,noteText))
        }
    }


}