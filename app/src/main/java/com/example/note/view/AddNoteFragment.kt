package com.example.note.view

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.note.R
import com.example.note.databinding.FragmentAddNoteBinding
import com.example.note.model.NoteData
import com.example.note.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddNoteFragment : Fragment() {

    private lateinit var addNoteBinding: FragmentAddNoteBinding
    private val noteViewModel: NoteViewModel by activityViewModels()
    var color: String = "none"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addNoteBinding = FragmentAddNoteBinding.inflate(inflater, container, false)

        addNoteBinding.addNote.setOnClickListener {
            createNotes()
        }

        addNoteBinding.toolbarSub.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_addNoteFragment_to_mainFragment)
        }


        return addNoteBinding.root
    }

    private fun createNotes() {
        if (TextUtils.isEmpty(addNoteBinding.edittextTitle.text.toString()) || TextUtils.isEmpty(addNoteBinding.edittextContent.text.toString())) {
            addNoteBinding.edittextTitle.error = "Please input title"
            addNoteBinding.edittextContent.error = "Please input content"
        } else {
            val title = addNoteBinding.edittextTitle.text.toString()
            val content = addNoteBinding.edittextContent.text.toString()
            val date = SimpleDateFormat("dd-MM-yyyy  ", Locale.getDefault()).format(Date())
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
                color = color,
                date = date
            )

            noteViewModel.insert(note)
            findNavController().navigate(R.id.action_addNoteFragment_to_mainFragment)
        }
    }

}