package com.example.academyhomework

import com.example.academyhomework.data.DataSource
import com.example.academyhomework.utils.Utils
import com.example.academyhomework.utils.Utils.stringWordsDivider
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun stringWordsDividerTest() {
        print(stringWordsDivider("embed — встраивать unscramble — Расшифровывать prior — перед straightforward — просто pane — часть окна"))
    }

    @Test
    fun makeHintTest(){
        var word = "Colloqium"
        var value:String = ""
        for (item in word.toList().shuffled()) {
            value = value.plus(item)
        }
        print(value)
    }
    @Test
    fun filterTest() {
        var words = listOf<String>("action","embeded","hello")
        words = words.filter {
            it.startsWith("ac",0,true)
        }
        print(words)
    }
}