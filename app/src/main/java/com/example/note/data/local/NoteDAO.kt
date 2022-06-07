package com.example.note.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.note.data.model.NoteData

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

    @Query("SELECT * FROM Note_table WHERE title LIKE :text OR content LIKE :text")
    fun search(text : String) : LiveData<List<NoteData>>

}