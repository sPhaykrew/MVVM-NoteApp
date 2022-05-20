package com.example.note.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.model.NoteDB
import com.example.note.model.NoteData
import com.example.note.model.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val getNote : LiveData<List<NoteData>> = repository.notedata
    var number = 0
    var number_list = MutableLiveData<Int>()

    fun insert(noteData: NoteData){
        viewModelScope.launch {Dispatchers.IO
            repository.insert(noteData)
        }
    }

    fun delete(noteData: NoteData){
        viewModelScope.launch {Dispatchers.IO
            repository.delete(noteData)
        }
    }

    fun loop(){
        Handler(Looper.getMainLooper()).postDelayed({
            number += 1
            number_list.value = number
            loop()
        },2000)
    }
}