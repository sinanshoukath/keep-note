/*
 * Created by sinan shoukath on 26/10/2023
 * Copyright (c) 2023. All rights reserved.
 */
package com.sinan.keepnote.presentation

import android.content.Context
import android.widget.Toast

fun Context.showToast(text: String) {
  val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
  toast.show()
}