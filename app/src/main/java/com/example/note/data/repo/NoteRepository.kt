package com.example.note.data.repo

import androidx.lifecycle.LiveData
import com.example.note.data.local.NoteDAO
import com.example.note.data.model.NoteData

class NoteRepository(private val dao : NoteDAO) {

    fun noteData() : LiveData<List<NoteData>> = dao.getAll()

    fun search(text : String) : LiveData<List<NoteData>> = dao.search(text)

    suspend fun insert(noteData : NoteData) = dao.insert(noteData)

    suspend fun delete(id : Int) = dao.delete(id)

    suspend fun update(noteData: NoteData) = dao.update(noteData)
}