package com.sinan.core.usecase

import com.sinan.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {
  suspend operator fun invoke(id: Long) = noteRepository.getNote(id)
}
