package com.example.note.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDAO {
    @Query("SELECT * FROM Note_table")
    fun getAll(): LiveData<List<NoteData>>

    @Insert
    suspend fun insert(note: NoteData)

    @Update
    suspend fun update(note : NoteData)

    @Query("DELETE FROM Note_table WHERE id = :id")
    suspend fun delete(id: Int)
}