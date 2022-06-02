package com.example.note.view

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.note.R
import com.example.note.databinding.FragmentEditNoteBinding
import com.example.note.model.NoteData
import com.example.note.viewmodel.NoteViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EditNoteFragment : Fragment() {

    private lateinit var editNoteBinding: FragmentEditNoteBinding
    private val noteViewModel : NoteViewModel by activityViewModels()
    private val note by navArgs<EditNoteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editNoteBinding = FragmentEditNoteBinding.inflate(inflater, container, false)

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

    private fun getNote(){
        editNoteBinding.edittextTitle.setText(note.data.title)
        editNoteBinding.edittextContent.setText(note.data.content)
        when (note.data.color) {
            "#" + Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.color1) and  0x00ffffff) -> editNoteBinding.color1.isChecked = true
            "#" + Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.color2) and  0x00ffffff) -> editNoteBinding.color2.isChecked = true
            "#" + Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.color3) and  0x00ffffff) -> editNoteBinding.color3.isChecked = true
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
                R.id.color1 -> "#" + Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.color1) and  0x00ffffff)
                R.id.color2 -> "#" + Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.color2) and  0x00ffffff)
                R.id.color3 -> "#" + Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.color3) and  0x00ffffff)
                else -> "#" + Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.color4) and  0x00ffffff)
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
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.delete_title_dialog))
            .setMessage(resources.getString(R.string.delete_message_dialog))
            .setNegativeButton(resources.getString(R.string.cancel_dialog)) { dialog, which ->
                //cancel
            }
            .setPositiveButton(resources.getString(R.string.delete_dialog)) { dialog, which ->
                noteViewModel.delete(note.data.id)
                findNavController().navigate(R.id.action_editNoteFragment_to_mainFragment)
            }
            .show()
    }

}