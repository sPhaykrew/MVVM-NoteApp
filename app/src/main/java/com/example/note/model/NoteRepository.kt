package com.example.note.model

import androidx.lifecycle.LiveData

class NoteRepository(private val dao : NoteDAO) {
    val notedata : LiveData<List<NoteData>> = dao.getAll()

    suspend fun insert(notedata : NoteData){
        dao.insert(notedata)
    }

    suspend fun delete(id : Int){
        dao.delete(id)
    }

    suspend fun update(notedata: NoteData){
        dao.update(notedata)
    }

}