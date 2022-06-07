package com.example.note.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.note.data.repo.NoteRepository
import com.example.note.ui.main.viewmodel.NoteViewModel

class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(repository) as T
        }

        throw IllegalAccessException("Unknow View model Class")
    }
}