/*
 * Created by sinan shoukath on 28/10/2023
 * Copyright (c) 2023. All rights reserved.
 */

package com.sinan.keepnote.framework.di

import com.sinan.core.repository.NoteRepository
import com.sinan.core.usecase.AddNote
import com.sinan.core.usecase.GetAllNotes
import com.sinan.core.usecase.GetNote
import com.sinan.core.usecase.GetWordCount
import com.sinan.core.usecase.RemoveNote
import com.sinan.keepnote.framework.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

  @Provides
  fun getUsesCases(repository: NoteRepository) = UseCases(
    AddNote(repository),
    GetAllNotes(repository),
    GetNote(repository),
    RemoveNote(repository),
    GetWordCount()
  )
}
