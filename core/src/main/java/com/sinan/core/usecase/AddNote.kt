package com.sinan.core.usecase

import com.sinan.core.data.Note
import com.sinan.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
  suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}
