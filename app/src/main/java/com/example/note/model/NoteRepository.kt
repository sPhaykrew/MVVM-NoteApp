package com.example.note.model

import androidx.lifecycle.LiveData

class NoteRepository(private val dao : NoteDAO) {

    fun noteData() : LiveData<List<NoteData>> = dao.getAll()

    suspend fun insert(noteData : NoteData) = dao.insert(noteData)

    suspend fun delete(id : Int) = dao.delete(id)

    suspend fun update(noteData: NoteData) = dao.update(noteData)

}