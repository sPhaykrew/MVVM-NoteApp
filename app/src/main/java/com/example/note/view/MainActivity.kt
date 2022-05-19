package com.example.note.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.R
import com.example.note.databinding.ActivityMainBinding
import com.example.note.model.NoteDAO
import com.example.note.model.NoteDB
import com.example.note.model.NoteData
import com.example.note.model.NoteRepository
import com.example.note.viewmodel.NoteViewModel
import com.example.note.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = NoteDB.getDatabase(applicationContext).NoteDAO()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(this,factory)[NoteViewModel::class.java]

        recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = recyclerViewAdapter

        noteViewModel.getNote.observe(this, Observer{
            recyclerViewAdapter.setItem(it)
            binding.recyclerview.adapter = recyclerViewAdapter
        })


        binding.floating.setOnClickListener {
            val note = NoteData(0,binding.edittextTitle.text.toString(),binding.edittextContent.text.toString())
            noteViewModel.insert(note)
        }
    }
}