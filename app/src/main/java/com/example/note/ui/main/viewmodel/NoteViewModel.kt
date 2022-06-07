package com.example.note.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.data.model.NoteData
import com.example.note.data.repo.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    fun getAllNote() : LiveData<List<NoteData>> = repository.noteData()

    fun search(text : String) : LiveData<List<NoteData>> = repository.search(text)

    fun insert(noteData: NoteData){
        viewModelScope.launch {Dispatchers.IO
            repository.insert(noteData)
        }
    }

    fun update(noteData : NoteData){
        viewModelScope.launch {Dispatchers.IO
        repository.update(noteData)
        }
    }

    fun delete(id : Int){
        viewModelScope.launch { Dispatchers.IO
        repository.delete(id)
        }
    }

}