package com.sinan.core.usecase

import com.sinan.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {
  suspend operator fun invoke() = noteRepository.getAllNotes()
}
