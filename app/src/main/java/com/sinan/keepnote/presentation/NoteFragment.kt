package com.sinan.keepnote.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.keepnote.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sinan.core.data.Note
import com.sinan.keepnote.framework.NoteViewModel


class NoteFragment : Fragment() {
  private lateinit var viewModel: NoteViewModel
  private lateinit var titleView: EditText
  private var currentNote = Note(0L, "", "", 0L, 0L)

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_note, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

    titleView = view.findViewById(R.id.titleView)
    val contentView = view.findViewById<EditText>(R.id.contentView)
    val saveButton = view.findViewById<FloatingActionButton>(R.id.saveNote)

    saveButton.setOnClickListener {
      if (titleView.text.isEmpty()) {
        context?.showToast("Title is empty")
        return@setOnClickListener
      }
      if (contentView.text.isEmpty()) {
        context?.showToast("Content is empty")
        return@setOnClickListener
      }
      val time = System.currentTimeMillis()
      currentNote.title = titleView.text.toString()
      currentNote.content = contentView.text.toString()
      currentNote.updateTime = time
      if (currentNote.id == 0L) {
        currentNote.creationTime = time
      }

      viewModel.saveNote(currentNote)
    }

    observeViewModel()
  }

  private fun observeViewModel() {
    viewModel.saved.observe(viewLifecycleOwner) {
      if (it) {
        context?.showToast("Done!")
        hideSoftKeyboard()
        findNavController().popBackStack()
      } else {
        context?.showToast("Something went wrong!")
      }
    }
  }

  private fun hideSoftKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(titleView.windowToken, 0)
  }
}