/*
 * Created by sinan shoukath on 28/10/2023
 * Copyright (c) 2023. All rights reserved.
 */

package com.sinan.keepnote.framework.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(val app: Application) {

  @Provides
  fun providesApp() = app
}