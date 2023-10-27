/*
 * Created by sinan shoukath on 26/10/2023
 * Copyright (c) 2023. All rights reserved.
 */
package com.sinan.core.usecase

import com.sinan.core.data.Note
import com.sinan.core.repository.NoteRepository

class RemoveNote(private val noteRepository: NoteRepository) {
  suspend operator fun invoke(note: Note) = noteRepository.removeNote(note)
}

