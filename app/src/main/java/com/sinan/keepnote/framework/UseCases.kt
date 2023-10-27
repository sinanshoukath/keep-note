/*
 * Created by sinan shoukath on 26/10/2023
 * Copyright (c) 2023. All rights reserved.
 */
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
