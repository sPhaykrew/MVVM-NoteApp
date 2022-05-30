package com.example.note.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Note_table")
@Parcelize
data class NoteData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val content: String,
    @ColumnInfo val color: String
//    @ColumnInfo val timestamp: Long,
) : Parcelable
