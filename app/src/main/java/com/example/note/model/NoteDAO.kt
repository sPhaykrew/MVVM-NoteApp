package com.example.note.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDAO {
    @Query("SELECT * FROM Note_table")
    fun getAll(): List<NoteData>

    @Insert
    fun insertAll(note: NoteData)

    @Delete
    fun delete(note: NoteData)
}