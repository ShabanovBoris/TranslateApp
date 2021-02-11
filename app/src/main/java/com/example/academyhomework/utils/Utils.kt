package com.example.academyhomework.utils

object Utils {

    fun stringWordsDivider(string: String): List<String> {
        var list: List<String> = string.trim()
            .replace(" — ","$",false)
            .replace(" ","$",false)
            .split("$")




        return list
    }}