package com.sinan.keepnote.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.keepnote.R
import com.sinan.core.data.Note
import java.text.SimpleDateFormat
import java.util.Date

class NotesListAdapter(var notes: MutableList<Note>): RecyclerView.Adapter<NotesListAdapter.NotesViewHolder>() {

  inner class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val titleView = itemView.findViewById<TextView>(R.id.title)
    private val contentView = itemView.findViewById<TextView>(R.id.content)
    private val dateView = itemView.findViewById<TextView>(R.id.date)

    fun bind(note: Note) {
      titleView.text = note.title
      contentView.text = note.content

      val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
      val resultDate = Date(note.updateTime)
      dateView.text = "Last updated: ${sdf.format(resultDate)}"
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
    return NotesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
  }

  override fun getItemCount() = notes.size

  override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
    holder.bind(notes[position])
  }

  fun updateNotes(newNotes: List<Note>) {
    notes.clear()
    notes.addAll(newNotes)
    notifyDataSetChanged()
  }
}
