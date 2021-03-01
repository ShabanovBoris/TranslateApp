package com.example.academyhomework.domain.features.englishToRussianCase


interface AnswerHandler {
    val action: (EnglishTranslate.TranslateVariants, Int) -> Unit
    fun handle(ans: EnglishTranslate.TranslateVariants, adapterPosition: Int)
}