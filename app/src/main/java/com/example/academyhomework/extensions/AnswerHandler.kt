package com.example.academyhomework.extensions

import com.example.academyhomework.model.EnglishTranslate


interface AnswerHandler {
    val action: (EnglishTranslate.TranslateVariants,Int) -> Unit
    fun handle(ans:EnglishTranslate.TranslateVariants,adapterPosition: Int)
}