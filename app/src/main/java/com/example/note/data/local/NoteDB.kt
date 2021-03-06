package com.example.note.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.note.data.model.NoteData

@Database(entities = [NoteData::class], version = 1)
abstract class NoteDB : RoomDatabase() {
    abstract fun NoteDAO() : NoteDAO

    companion object{
        @Volatile
        private var INSTANCE : NoteDB? = null
        fun getDatabase(context: Context): NoteDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDB::class.java,
                        "note_database"
                    ).build()
                }
                return instance
            }
        }
    }
}