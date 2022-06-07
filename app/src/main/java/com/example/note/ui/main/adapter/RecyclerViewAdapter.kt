package com.example.note.ui.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.note.databinding.RecyclerviewItemBinding
import com.example.note.data.model.NoteData
import com.example.note.ui.main.view.MainFragmentDirections

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var item : List<NoteData> = emptyList()

    fun setItem(item : List<NoteData>){
        this.item  = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.title.text = item[position].title
        holder.binding.content.text = item[position].content
        holder.binding.date.text = item[position].date
        holder.binding.color.setBackgroundColor(Color.parseColor(item[position].color))
        holder.binding.root.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToEditNoteFragment(item[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    inner class ViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)

}