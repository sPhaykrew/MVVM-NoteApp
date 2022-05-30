package com.example.note.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.note.R
import com.example.note.databinding.FragmentMainBinding
import com.example.note.model.NoteDB
import com.example.note.model.NoteRepository
import com.example.note.viewmodel.NoteViewModel
import com.example.note.viewmodel.NoteViewModelFactory

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        initViewModel()
        initRecyclerView()

        noteViewModel.getNote.observe(viewLifecycleOwner, Observer{
            recyclerViewAdapter.setItem(it)
            binding.recyclerview.adapter = recyclerViewAdapter
        })

        binding.addNote.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addNoteFragment)
        }

        return binding.root
    }

        private fun initViewModel(){
        val dao = NoteDB.getDatabase(requireActivity().application).NoteDAO()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(requireActivity(),factory)[NoteViewModel::class.java]
    }

    private fun initRecyclerView(){
        recyclerViewAdapter = RecyclerViewAdapter()
//        binding.recyclerview.adapter = recyclerViewAdapter
        binding.recyclerview.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
    }

}