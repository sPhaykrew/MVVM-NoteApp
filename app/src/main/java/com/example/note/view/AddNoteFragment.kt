package com.example.note.view

import android.os.Bundle
import android.text.TextUtils
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

    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    var color: String = "#FFBB86FC"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)

        initViewModel()

        binding.addNote.setOnClickListener {
            setNotes()
        }

        return binding.root
    }

    private fun initViewModel() {
        val dao = NoteDB.getDatabase(requireActivity().application).NoteDAO()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(requireActivity(), factory)[NoteViewModel::class.java]
    }

    private fun setNotes() {
        if (TextUtils.isEmpty(binding.edittextTitle.text.toString()) || TextUtils.isEmpty(binding.edittextContent.text.toString())) {
            binding.edittextTitle.error = "Please input title"
            binding.edittextContent.error = "Please input content"
        } else {
            val note = NoteData(
                0,
                binding.edittextTitle.text.toString(),
                binding.edittextContent.text.toString(),
                when (binding.colorGroup.checkedRadioButtonId) {
                    R.id.color1 -> "#FFBB86FC"
                    R.id.color2 -> "#FF3700B3"
                    R.id.color3 -> "#FF03DAC5"
                    else -> "#FF018786"
                }
            )
            noteViewModel.insert(note)
            findNavController().navigate(R.id.mainFragment)
        }
    }

}