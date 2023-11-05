/*
 * Created by sinan shoukath on 26/10/2023
 * Copyright (c) 2023. All rights reserved.
 */
package com.sinan.core.data

data class Note(
  var id: Long = 0,
  var title: String,
  var content: String,
  var creationTime: Long,
  var updateTime: Long,
  var wordCount: Int = 0
)

