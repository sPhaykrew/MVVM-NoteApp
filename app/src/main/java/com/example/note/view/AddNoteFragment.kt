package com.example.note.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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

    private lateinit var addNoteBinding: FragmentAddNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    var color: String = "none"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addNoteBinding = FragmentAddNoteBinding.inflate(inflater, container, false)

        initViewModel()

        addNoteBinding.addNote.setOnClickListener {
            createNotes()
        }


        addNoteBinding.toolbarSub.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_addNoteFragment_to_mainFragment)
        }


        return addNoteBinding.root
    }

    private fun initViewModel() {
        val dao = NoteDB.getDatabase(requireActivity().application).NoteDAO()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(requireActivity(), factory)[NoteViewModel::class.java]
    }

    private fun createNotes() {
        if (TextUtils.isEmpty(addNoteBinding.edittextTitle.text.toString()) || TextUtils.isEmpty(addNoteBinding.edittextContent.text.toString())) {
            addNoteBinding.edittextTitle.error = "Please input title"
            addNoteBinding.edittextContent.error = "Please input content"
        } else {
            val title = addNoteBinding.edittextTitle.text.toString()
            val content = addNoteBinding.edittextContent.text.toString()
            val color = when (addNoteBinding.colorGroup.checkedRadioButtonId) {
                R.id.color1 -> "#" + Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.color1) and  0x00ffffff)
                R.id.color2 -> "#" + Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.color2) and  0x00ffffff)
                R.id.color3 -> "#" + Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.color3) and  0x00ffffff)
                else -> "#" + Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.color4) and  0x00ffffff)
            }

            val note = NoteData(
                id = 0,
                title = title,
                content = content,
                color = color
            )

            noteViewModel.insert(note)
            findNavController().navigate(R.id.action_addNoteFragment_to_mainFragment)
        }
    }

}