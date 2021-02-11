package com.example.academyhomework.data

import android.content.Context
import com.example.academyhomework.MainActivity
import com.example.academyhomework.R
import com.example.academyhomework.model.Dword
import com.example.academyhomework.utils.Utils.stringWordsDivider

class DataSource {
    fun loadWords(context: Context):List<Dword>{
        val wordList:List<String> = stringWordsDivider(
             context.resources.getString(R.string.word_collection))
        var dwordList = mutableListOf<Dword>(Dword())

        for (item in wordList ){
            when {
                dwordList.last().word == "nope" -> dwordList.add(Dword(item))
                dwordList.last().translate == "nope" -> dwordList.last().translate = item
                else -> dwordList.add(Dword(item))
            }
        }
        return dwordList

    }
}