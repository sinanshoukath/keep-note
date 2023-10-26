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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesListViewModel(application: Application): AndroidViewModel(application) {
  val scope = CoroutineScope(Dispatchers.IO)
  private val repository = NoteRepository(RoomNoteDataSource(application))
  private val useCases = UseCases(
    AddNote(repository),
    GetAllNotes(repository),
    GetNote(repository),
    RemoveNote(repository)
  )
  val notesListLiveData = MutableLiveData<List<Note>>()

  fun getNotes() {
    scope.launch {
      notesListLiveData.postValue(useCases.getAllNotes())
    }
  }
}