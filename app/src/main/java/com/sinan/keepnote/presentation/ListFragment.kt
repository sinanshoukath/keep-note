package com.sinan.keepnote.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keepnote.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sinan.keepnote.framework.NotesListViewModel
import java.util.logging.Logger


class ListFragment : Fragment(), ListAction {
  private lateinit var viewModel: NotesListViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_list, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(this).get(NotesListViewModel::class.java)
    val addNote = view.findViewById<FloatingActionButton>(R.id.addNote)
    val notesListView = view.findViewById<RecyclerView>(R.id.notesListView).apply { layoutManager = LinearLayoutManager(context) }
    val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
    val adapter = NotesListAdapter(this.context, mutableListOf(), this)
    notesListView.adapter = adapter
    viewModel.notesListLiveData.observe(viewLifecycleOwner) {
      progressBar.visibility = View.GONE
      adapter.updateNotes(it)
    }
    addNote.setOnClickListener {
      goToNoteDetails()
    }
    viewModel.getNotes()
  }

  private fun goToNoteDetails(id: Long = 0L) {
    val bundle = Bundle()
    bundle.putLong(getString(R.string.noteid), id)
    findNavController().navigate(R.id.action_listFragment_to_noteFragment, bundle)
  }

  override fun onClick(id: Long) {
    goToNoteDetails(id)
  }
}
