/*
 * Created by sinan shoukath on 26/10/2023
 * Copyright (c) 2023. All rights reserved.
 */
package com.sinan.keepnote.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.keepnote.R
import com.example.keepnote.databinding.ItemNoteBinding
import com.sinan.core.data.Note
import java.text.SimpleDateFormat
import java.util.Date

class NotesListAdapter(val context: Context?, private var notes: MutableList<Note>, val actions: ListAction): RecyclerView.Adapter<NotesListAdapter.NotesViewHolder>() {

  inner class NotesViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SimpleDateFormat")
    fun bind(note: Note) {
      binding.title.text = note.title
      binding.content.text = note.content

      val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
      val resultDate = Date(note.updateTime)
      binding.date.text = context?.getString(R.string.last_updated, sdf.format(resultDate))
      binding.wordCount.text = context?.getString(R.string.words, note.wordCount.toString())

      itemView.setOnClickListener { actions.onClick(note.id) }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
    return NotesViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }

  override fun getItemCount() = notes.size

  override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
    holder.bind(notes[position])
  }

  @SuppressLint("NotifyDataSetChanged")
  fun updateNotes(newNotes: List<Note>) {
    notes.clear()
    notes.addAll(newNotes)
    notifyDataSetChanged()
  }
}
