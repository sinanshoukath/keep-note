/*
 * Created by sinan shoukath on 26/10/2023
 * Copyright (c) 2023. All rights reserved.
 */
package com.sinan.keepnote.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query

@Dao
interface NoteDao {

  @Insert(onConflict = REPLACE)
  suspend fun addNoteEntity(noteEntity: NoteEntity)

  @Query("SELECT * From note WHERE id = :id")
  suspend fun getNoteEntity(id: Long): NoteEntity?

  @Query("SELECT * From note")
  suspend fun getAllNoteEntities(): List<NoteEntity>

  @Delete
  suspend fun deleteNoteEntity(noteEntity: NoteEntity)
}
