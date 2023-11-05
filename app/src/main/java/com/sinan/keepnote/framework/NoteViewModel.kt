/*
 * Created by sinan shoukath on 26/10/2023
 * Copyright (c) 2023. All rights reserved.
 */
package com.sinan.keepnote.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sinan.core.data.Note
import com.sinan.keepnote.framework.di.ApplicationModule
import com.sinan.keepnote.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel(application: Application): AndroidViewModel(application) {
  private val scope = CoroutineScope(Dispatchers.IO)

  @Inject
  lateinit var useCases: UseCases
  val saved = MutableLiveData<Boolean>()
  val currentNote = MutableLiveData<Note?>()

  init {
    DaggerViewModelComponent.builder()
      .applicationModule(ApplicationModule(getApplication()))
      .build()
      .inject(this)
  }

  fun saveNote(note: Note) {
    scope.launch {
      useCases.addNote(note)
      saved.postValue(true)
    }
  }

  fun getNote(id: Long) {
    scope.launch {
      val note = useCases.getNote(id)
      currentNote.postValue(note)
    }
  }

  fun deleteNote(note: Note) {
    scope.launch {
      useCases.removeNote(note)
      saved.postValue(true)
    }
  }
}