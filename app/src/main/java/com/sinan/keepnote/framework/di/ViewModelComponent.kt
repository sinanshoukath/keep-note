/*
 * Created by sinan shoukath on 28/10/2023
 * Copyright (c) 2023. All rights reserved.
 */

package com.sinan.keepnote.framework.di

import com.sinan.keepnote.framework.NoteViewModel
import com.sinan.keepnote.framework.NotesListViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
  fun inject(noteViewModel: NoteViewModel)
  fun inject(notesListViewModel: NotesListViewModel)
}
