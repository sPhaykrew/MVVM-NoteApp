package com.example.note.view

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.note.R
import com.example.note.databinding.FragmentEditNoteBinding
import com.example.note.model.NoteDB
import com.example.note.model.NoteData
import com.example.note.model.NoteRepository
import com.example.note.viewmodel.NoteViewModel
import com.example.note.viewmodel.NoteViewModelFactory

class EditNoteFragment : Fragment() {

    private lateinit var editNoteBinding: FragmentEditNoteBinding
    private lateinit var noteViewModel : NoteViewModel
    private val note by navArgs<EditNoteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editNoteBinding = FragmentEditNoteBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        initViewModel()
        getNote()

        editNoteBinding.save.setOnClickListener {
            updateNote()
        }

        editNoteBinding.toolbarSub.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_editNoteFragment_to_mainFragment)
        }

        editNoteBinding.toolbarSub.setOnMenuItemClickListener {
            if (it.itemId == R.id.menu_delete){
                deleteNote()
            }
            true
        }

        return editNoteBinding.root
    }

    private fun initViewModel() {
        val dao = NoteDB.getDatabase(requireActivity().application).NoteDAO()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(requireActivity(), factory)[NoteViewModel::class.java]
    }

    private fun getNote(){
        editNoteBinding.edittextTitle.setText(note.data.title)
        editNoteBinding.edittextContent.setText(note.data.content)
        when (note.data.color) {
            "#FFBB86FC" -> editNoteBinding.color1.isChecked = true
            "#FF3700B3" -> editNoteBinding.color2.isChecked = true
            "#FF03DAC5" -> editNoteBinding.color3.isChecked = true
            else -> editNoteBinding.color4.isChecked = true
        }
    }

    private fun updateNote(){
        if (TextUtils.isEmpty(editNoteBinding.edittextTitle.text.toString()) || TextUtils.isEmpty(editNoteBinding.edittextContent.text.toString())) {
            editNoteBinding.edittextTitle.error = "Please input title"
            editNoteBinding.edittextContent.error = "Please input content"
        } else {
            val title = editNoteBinding.edittextTitle.text.toString()
            val content = editNoteBinding.edittextContent.text.toString()
            val color = when (editNoteBinding.colorGroup.checkedRadioButtonId) {
                R.id.color1 -> "#FFBB86FC"
                R.id.color2 -> "#FF3700B3"
                R.id.color3 -> "#FF03DAC5"
                else -> "#FF018786"
            }

            val note = NoteData(
                id = note.data.id,
                title = title,
                content = content,
                color = color
            )

            noteViewModel.update(note)
            findNavController().navigate(R.id.action_editNoteFragment_to_mainFragment)
        }
    }

    private fun deleteNote(){
        noteViewModel.delete(note.data.id)
        findNavController().navigate(R.id.action_editNoteFragment_to_mainFragment)
    }

}