/*
 * Created by sinan shoukath on 26/10/2023
 * Copyright (c) 2023. All rights reserved.
 */
package com.sinan.keepnote.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepnote.databinding.FragmentListBinding
import com.sinan.keepnote.framework.NotesListViewModel


class ListFragment : Fragment(), ListAction {
  private lateinit var viewModel: NotesListViewModel
  private lateinit var binding: FragmentListBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentListBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(this)[NotesListViewModel::class.java]
    binding.notesListView.layoutManager = LinearLayoutManager(context)
    val adapter = NotesListAdapter(this.context, mutableListOf(), this)
    binding.notesListView.adapter = adapter
    viewModel.notesListLiveData.observe(viewLifecycleOwner) {
      binding.progressBar.visibility = View.GONE
      adapter.updateNotes(it)
    }
    binding.addNote.setOnClickListener {
      goToNoteDetails()
    }
    viewModel.getNotes()
  }

  private fun goToNoteDetails(id: Long = 0L) {
    val action = ListFragmentDirections.actionListFragmentToNoteFragment(id)
    findNavController().navigate(action)
  }

  override fun onClick(id: Long) {
    goToNoteDetails(id)
  }
}
