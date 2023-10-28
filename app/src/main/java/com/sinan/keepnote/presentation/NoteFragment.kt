/*
 * Created by sinan shoukath on 26/10/2023
 * Copyright (c) 2023. All rights reserved.
 */
package com.sinan.keepnote.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.keepnote.R
import com.example.keepnote.databinding.FragmentNoteBinding
import com.sinan.core.data.Note
import com.sinan.keepnote.framework.NoteViewModel


class NoteFragment : Fragment() {
  private lateinit var viewModel: NoteViewModel
  private lateinit var binding: FragmentNoteBinding
  private var noteId: Long? = null
  private var currentNote = Note(0L, "", "", 0L, 0L)
  private val args: NoteFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    // Inflate the layout for this fragment
    noteId = args.component1()
    binding = FragmentNoteBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val menuHost: MenuHost = requireActivity()
    menuHost.addMenuProvider(object : MenuProvider {
      override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        // Add menu items here
        menuInflater.inflate(R.menu.note_menu, menu)
      }

      override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        // Handle the menu selection
        when(menuItem.itemId) {
          R.id.delete -> {
            if (noteId != 0L) {
              AlertDialog.Builder(context!!)
                .setTitle(getString(R.string.delete_note))
                .setMessage(getString(R.string.delete_message))
                .setPositiveButton(getString(R.string.yes)) { _, _ ->
                  viewModel.deleteNote(currentNote)
                }
                .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
                .create()
                .show()
            }
          }
        }
        return true
      }
    }, viewLifecycleOwner, Lifecycle.State.RESUMED)


    viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

    binding.saveNote.setOnClickListener {
      if (binding.titleView.text.isEmpty()) {
        context?.showToast(getString(R.string.enter_the_title))
        return@setOnClickListener
      }
      if (binding.contentView.text.isEmpty()) {
        context?.showToast(getString(R.string.enter_the_content))
        return@setOnClickListener
      }
      val time = System.currentTimeMillis()
      currentNote.title = binding.titleView.text.toString()
      currentNote.content = binding.contentView.text.toString()
      currentNote.updateTime = time
      if (currentNote.id == 0L) {
        currentNote.creationTime = time
      }

      viewModel.saveNote(currentNote)
    }

    observeViewModel()
    noteId?.let {
      viewModel.getNote(it)
    }
  }

  private fun observeViewModel() {
    viewModel.saved.observe(viewLifecycleOwner) {
      if (it) {
        context?.showToast(getString(R.string.done))
        hideSoftKeyboard()
        findNavController().popBackStack()
      } else {
        context?.showToast(getString(R.string.something_went_wrong))
      }
    }

    viewModel.currentNote.observe(viewLifecycleOwner) {
      it?.let {
        currentNote = it
        binding.titleView.setText(it.title, TextView.BufferType.EDITABLE)
        binding.contentView.setText(it.content,  TextView.BufferType.EDITABLE)
      }
    }
  }

  private fun hideSoftKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(binding.titleView.windowToken, 0)
  }
}
