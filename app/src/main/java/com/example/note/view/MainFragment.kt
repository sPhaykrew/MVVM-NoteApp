package com.example.note.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.note.R
import com.example.note.databinding.FragmentMainBinding
import com.example.note.model.NoteDB
import com.example.note.model.NoteData
import com.example.note.model.NoteRepository
import com.example.note.viewmodel.NoteViewModel
import com.example.note.viewmodel.NoteViewModelFactory
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private var notes: List<NoteData> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        initViewModel()
        initRecyclerView()

        noteViewModel.getNote.observe(viewLifecycleOwner, Observer {
            notes = it
            recyclerViewAdapter.setItem(it)
            binding.recyclerview.adapter = recyclerViewAdapter
        })

        binding.addNote.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addNoteFragment)
        }

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                noteFilter(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        return binding.root
    }

    private fun initViewModel() {
        val dao = NoteDB.getDatabase(requireActivity().application).NoteDAO()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(requireActivity(), factory)[NoteViewModel::class.java]
    }

    private fun initRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun noteFilter(text: String) {
        val filter = ArrayList<NoteData>()
        for (note in notes) {
            if (note.title.contains(text) || note.content.contains(text)) {
                filter.add(note)
            }
        }
        recyclerViewAdapter.setItem(filter)
        recyclerViewAdapter.notifyDataSetChanged()
    }

}