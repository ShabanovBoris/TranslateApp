package com.example.academyhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import com.example.academyhomework.data.DataSource
import com.example.academyhomework.model.Dword

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


    fun onClickImage1(view: View){
        findViewById<androidx.fragment.app.FragmentContainerView>(R.id.main_container)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, imageFragment.newInstance(R.drawable.ic_baseline_directions_bike_24))
            .commit()
    }

    fun onClickImage2(view: View){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, imageFragment.newInstance(R.drawable.ic_baseline_directions_run_24))
            .commitNowAllowingStateLoss()
    }

    fun onClickImage3(view: View){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, imageFragment.newInstance(R.drawable.ic_baseline_drive_eta_24))
            .commit()
    }

}