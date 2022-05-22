package com.example.note.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.model.NoteData
import com.example.note.model.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val getNote : LiveData<List<NoteData>> = repository.notedata

    fun insert(noteData: NoteData){
        viewModelScope.launch {Dispatchers.IO
            repository.insert(noteData)
        }
    }

}