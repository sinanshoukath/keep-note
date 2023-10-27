/*
 * Created by sinan shoukath on 26/10/2023
 * Copyright (c) 2023. All rights reserved.
 */
package com.sinan.core.repository

import com.sinan.core.data.Note

class NoteRepository(private val dataSource: NoteDataSource) {
  suspend fun addNote(note: Note) = dataSource.add(note)

  suspend fun getNote(id: Long): Note? = dataSource.get(id)

  suspend fun getAllNotes(): List<Note> = dataSource.getAll()

  suspend fun removeNote(note: Note) = dataSource.remove(note)
}
