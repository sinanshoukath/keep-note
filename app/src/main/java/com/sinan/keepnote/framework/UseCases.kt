package com.sinan.keepnote.framework

import com.sinan.core.usecase.AddNote
import com.sinan.core.usecase.GetAllNotes
import com.sinan.core.usecase.GetNote
import com.sinan.core.usecase.RemoveNote

data class UseCases(
  val addNote: AddNote,
  val getAllNotes: GetAllNotes,
  val getNote: GetNote,
  val removeNote: RemoveNote
)
