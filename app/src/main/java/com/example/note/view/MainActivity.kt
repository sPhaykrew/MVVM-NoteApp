package com.example.note.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.note.R
import com.example.note.databinding.ActivityMainBinding
import com.example.note.model.NoteDB
import com.example.note.model.NoteRepository
import com.example.note.viewmodel.NoteViewModel
import com.example.note.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var navController : NavController
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun initViewModel() {
        val dao = NoteDB.getDatabase(this).NoteDAO()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]
    }
}