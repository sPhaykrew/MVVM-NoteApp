package com.example.note.view

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.note.R
import com.example.note.databinding.FragmentAddNoteBinding
import com.example.note.model.NoteDB
import com.example.note.model.NoteData
import com.example.note.model.NoteRepository
import com.example.note.viewmodel.NoteViewModel
import com.example.note.viewmodel.NoteViewModelFactory

class AddNoteFragment : Fragment() {

    private lateinit var binding : FragmentAddNoteBinding
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)

        initViewModel()

        binding.addNote.setOnClickListener{
            if (TextUtils.isEmpty(binding.edittextTitle.text.toString()) || TextUtils.isEmpty(binding.edittextContent.text.toString())){
                binding.edittextTitle.error = "Please input title"
                binding.edittextContent.error = "Please input content"
            } else {
                val note = NoteData(0,binding.edittextTitle.text.toString(),binding.edittextContent.text.toString())
                noteViewModel.insert(note)
                findNavController().navigate(R.id.mainFragment)
            }
        }

        return binding.root
    }

    private fun initViewModel(){
        val dao = NoteDB.getDatabase(requireActivity().application).NoteDAO()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(requireActivity(),factory)[NoteViewModel::class.java]
    }

}