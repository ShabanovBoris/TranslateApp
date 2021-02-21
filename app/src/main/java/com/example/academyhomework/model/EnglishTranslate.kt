package com.example.academyhomework.model

import com.example.academyhomework.data.DataSource



data class EnglishTranslate(
    val engWord:String,
    val translateRus: String,
    val hint:String
){

   enum class TranslateVariants(){
        GOOD,
       WRONG
    }
}
