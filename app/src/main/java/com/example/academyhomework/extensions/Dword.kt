package com.example.academyhomework.extensions

import com.example.academyhomework.model.Dword
import com.example.academyhomework.model.EnglishTranslate
import com.example.academyhomework.model.RussianTranslate
import com.example.academyhomework.utils.Utils

fun Dword.toRussianTranslate():RussianTranslate{
    return RussianTranslate(
        rusWord = translate,
        translateEng = word,
        hint = Utils.makeHint(word)
    )
}

fun List<Dword>.toEnglishTranslateList():List<EnglishTranslate>{

    var newList:MutableList<EnglishTranslate> = mutableListOf(EnglishTranslate("nope","nope"))
    forEach { newList?.add(it.toEnglishTranslate()) }


    return newList
}

fun Dword.toEnglishTranslate():EnglishTranslate{
    return EnglishTranslate(
        engWord = word,
        translateRus = translate,
        hint = Utils.makeHint(translate)
    )
}