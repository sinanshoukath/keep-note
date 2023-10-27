/*
 * Created by sinan shoukath on 26/10/2023
 * Copyright (c) 2023. All rights reserved.
 */
package com.sinan.keepnote.framework

import android.content.Context
import com.sinan.core.data.Note
import com.sinan.core.repository.NoteDataSource
import com.sinan.keepnote.framework.db.DatabaseService
import com.sinan.keepnote.framework.db.NoteEntity

class RoomNoteDataSource(context: Context): NoteDataSource {
  val noteDao = DatabaseService.getInstance(context).noteDao()

  override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

  override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNote()

  override suspend fun getAll(): List<Note> = noteDao.getAllNoteEntities().map { it.toNote() }

  override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}
