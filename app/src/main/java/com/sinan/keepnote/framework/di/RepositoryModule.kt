/*
 * Created by sinan shoukath on 28/10/2023
 * Copyright (c) 2023. All rights reserved.
 */

package com.sinan.keepnote.framework.di

import android.app.Application
import com.sinan.core.repository.NoteRepository
import com.sinan.keepnote.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

  @Provides
  fun providesRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}