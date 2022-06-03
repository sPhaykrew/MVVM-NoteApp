package com.example.note.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.note.R
import com.example.note.databinding.FragmentMainBinding
import com.example.note.model.NoteData
import com.example.note.viewmodel.NoteViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val noteViewModel: NoteViewModel by activityViewModels()
    private var notes: List<NoteData> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        initRecyclerView()

        noteViewModel.getAllNote().observe(viewLifecycleOwner, Observer {
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
                val noteFiler = noteViewModel.noteFilter(p0.toString(),notes)
                recyclerViewAdapter.setItem(noteFiler)
                recyclerViewAdapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        return binding.root
    }

    private fun initRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

}