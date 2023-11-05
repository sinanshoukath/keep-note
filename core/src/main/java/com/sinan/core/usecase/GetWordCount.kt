/*
 * Created by sinan shoukath on 28/10/2023
 * Copyright (c) 2023. All rights reserved.
 */

package com.sinan.core.usecase

import com.sinan.core.data.Note

class GetWordCount {
  operator fun invoke(note: Note) = getCount(note.title) + getCount(note.content)

  private fun getCount(str: String): Int {
    val regex = Regex("\\s+")
    val words = str.split(regex)
    return words.count { it.isNotEmpty() }
  }
}