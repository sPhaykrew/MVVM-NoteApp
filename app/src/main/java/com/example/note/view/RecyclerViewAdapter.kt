package com.example.note.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.model.NoteData

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var item : List<NoteData> = emptyList()

    fun setItem(item : List<NoteData>){
        this.item  = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.title.text = item[position].title
        holder.content.text = item[position].content
        holder.color.setCardBackgroundColor(Color.parseColor(item[position].color))
    }

    override fun getItemCount(): Int {
        return item.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var content: TextView = view.findViewById(R.id.content)
        var color: CardView = view.findViewById(R.id.color)
    }
}