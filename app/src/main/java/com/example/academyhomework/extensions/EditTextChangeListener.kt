package com.example.academyhomework.extensions

import android.text.TextWatcher

interface EditTextChangeListener {
    val action:(CharSequence?,Int,Int,Int) -> Unit
}