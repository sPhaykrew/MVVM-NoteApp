package com.example.note.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDAO {
    @Query("SELECT * FROM Note_table")
    fun getAll(): LiveData<List<NoteData>>

    @Insert
    suspend fun insert(note: NoteData)

    @Delete
    suspend fun delete(note: NoteData)
}