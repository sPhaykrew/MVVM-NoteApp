package com.example.note.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note_table")
data class NoteData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val title: String?,
    @ColumnInfo val content: String?,
//    @ColumnInfo val timestamp: Long,
//    @ColumnInfo val color: Int
)
