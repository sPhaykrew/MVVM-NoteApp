package com.example.note.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NoteRepository(private val dao : NoteDAO) {
    val notedata : LiveData<List<NoteData>> = dao.getAll()

    suspend fun insert(notedata : NoteData){
        dao.insert(notedata)
    }

    suspend fun delete(notedata : NoteData){
        dao.delete(notedata)
    }
}