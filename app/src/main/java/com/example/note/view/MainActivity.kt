package com.example.note.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.note.databinding.ActivityMainBinding
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

        initViewModel()
        initRecyclerView()

        noteViewModel.getNote.observe(this, Observer{
            recyclerViewAdapter.setItem(it)
//            binding.recyclerview.adapter = recyclerViewAdapter
            recyclerViewAdapter.notifyDataSetChanged()
        })

//        noteViewModel.loop()
//
//        noteViewModel.number_list.observe(this, Observer {
//            binding.header.text = it.toString()
//        })


        binding.addNote.setOnClickListener {
            val intent = Intent(this, AddNotesActivity::class.java)
            startActivity(intent)
//            val note = NoteData(0,binding.edittextTitle.text.toString(),binding.edittextContent.text.toString())
//            noteViewModel.insert(note)
        }
    }

    private fun initViewModel(){
        val dao = NoteDB.getDatabase(applicationContext).NoteDAO()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(this,factory)[NoteViewModel::class.java]
    }

    private fun initRecyclerView(){
        recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyclerview.adapter = recyclerViewAdapter
        binding.recyclerview.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
    }
}