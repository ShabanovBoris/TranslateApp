package com.example.academyhomework

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.navigation.fragment.NavHostFragment
import com.example.academyhomework.data.DataSource
import com.example.academyhomework.extensions.Throughoutable
import com.example.academyhomework.model.Dword

class MainActivity : AppCompatActivity(), Throughoutable {

    var fragment :androidx.fragment.app.FragmentContainerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment = findViewById<androidx.fragment.app.FragmentContainerView>(R.id.main_container)

    }


    fun onClickImage1(view: View){
        fragment?.visibility = View.VISIBLE
        findViewById<androidx.fragment.app.FragmentContainerView>(R.id.main_container)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, imageFragment.newInstance(R.drawable.ic_baseline_directions_bike_24))
            .commit()
    }

    fun onClickImage2(view: View){
        fragment?.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, imageFragment.newInstance(R.drawable.ic_baseline_directions_run_24))
            .commitNowAllowingStateLoss()
    }

    fun onClickImage3(view: View){
        fragment?.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, imageFragment.newInstance(R.drawable.ic_baseline_drive_eta_24))
            .commit()
    }

    override fun onClickToHide() {

        findViewById<Button>(R.id.imageButton1).visibility = View.GONE
        findViewById<Button>(R.id.imageButton2).visibility = View.GONE
        findViewById<Button>(R.id.imageButton3).visibility = View.GONE
        fragment?.visibility = View.GONE
    }

}