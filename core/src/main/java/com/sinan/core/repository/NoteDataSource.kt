/*
 * Created by sinan shoukath on 26/10/2023
 * Copyright (c) 2023. All rights reserved.
 */
package com.sinan.core.repository

import com.sinan.core.data.Note

interface NoteDataSource {
  suspend fun add(note: Note)

  suspend fun get(id: Long): Note?

  suspend fun getAll(): List<Note>

  suspend fun remove(note: Note)
}