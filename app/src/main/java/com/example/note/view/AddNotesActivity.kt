package com.example.note.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.note.databinding.ActivityMainBinding
import com.example.note.databinding.AddNotesBinding
import com.example.note.model.NoteDB
import com.example.note.model.NoteData
import com.example.note.model.NoteRepository
import com.example.note.viewmodel.NoteViewModel
import com.example.note.viewmodel.NoteViewModelFactory


class AddNotesActivity : AppCompatActivity() {

    private lateinit var notesBinding: AddNotesBinding
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notesBinding = AddNotesBinding.inflate(layoutInflater)
        setContentView(notesBinding.root)

        initViewModel()

        notesBinding.addNote.setOnClickListener{
            val note = NoteData(0,notesBinding.edittextTitle.text.toString(),notesBinding.edittextContent.text.toString())
            noteViewModel.insert(note)
            finish()
        }

    }

    private fun initViewModel(){
        val dao = NoteDB.getDatabase(applicationContext).NoteDAO()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(this,factory)[NoteViewModel::class.java]
    }
}