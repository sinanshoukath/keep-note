/*
 * Created by sinan shoukath on 26/10/2023
 * Copyright (c) 2023. All rights reserved.
 */
package com.sinan.keepnote.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sinan.core.data.Note
import com.sinan.core.repository.NoteRepository
import com.sinan.core.usecase.AddNote
import com.sinan.core.usecase.GetAllNotes
import com.sinan.core.usecase.GetNote
import com.sinan.core.usecase.RemoveNote
import com.sinan.keepnote.framework.di.ApplicationModule
import com.sinan.keepnote.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesListViewModel(application: Application): AndroidViewModel(application) {
  val scope = CoroutineScope(Dispatchers.IO)
  @Inject
  lateinit var useCases: UseCases
  val notesListLiveData = MutableLiveData<List<Note>>()

  init {
    DaggerViewModelComponent.builder()
      .applicationModule(ApplicationModule(getApplication()))
      .build()
      .inject(this)
  }

  fun getNotes() {
    scope.launch {
      val notes = useCases.getAllNotes()
      notes.forEach {
        it.wordCount = useCases.getWordCount.invoke(it)
      }
      notesListLiveData.postValue(notes)
    }
  }
}