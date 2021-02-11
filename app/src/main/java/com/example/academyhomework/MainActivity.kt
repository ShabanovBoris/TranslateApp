package com.example.academyhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.academyhomework.data.DataSource
import com.example.academyhomework.model.Dword

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataSource:List<Dword> = DataSource().loadWords(this)
        Log.d("dataSource", dataSource.toString())

    }
}