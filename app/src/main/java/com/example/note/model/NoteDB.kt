package com.example.note.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteData::class], version = 1)
abstract class NoteDB : RoomDatabase() {
    abstract fun NoteDAO() : NoteDAO

    companion object{
        @Volatile
        private var INSTANCE : NoteDB? = null
        fun getDatabase(context: Context): NoteDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDB::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}