package com.example.academyhomework

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import com.example.academyhomework.extensions.Throughoutable
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), Throughoutable {


    var fragment :androidx.fragment.app.FragmentContainerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment = findViewById<androidx.fragment.app.FragmentContainerView>(R.id.main_container)


    }




    override fun onClickToHide(view: View) {

        findViewById<Button>(R.id.imageButton1).visibility = View.GONE
        findViewById<Button>(R.id.imageButton2).visibility = View.GONE
        findViewById<Button>(R.id.imageButton3).visibility = View.GONE
        view.visibility = View.GONE
        fragment?.visibility = View.GONE
        val snackbar = Snackbar.make(fragment!!.rootView,"Pictures are coming back?",Snackbar.LENGTH_INDEFINITE)
            .setAction("undo"){
                findViewById<Button>(R.id.imageButton1).visibility = View.VISIBLE
                findViewById<Button>(R.id.imageButton2).visibility = View.VISIBLE
                findViewById<Button>(R.id.imageButton3).visibility = View.VISIBLE
                fragment?.visibility = View.VISIBLE
                view.visibility = View.VISIBLE

            }.setActionTextColor(Color.BLACK)
        val params = FrameLayout.LayoutParams(snackbar.view.layoutParams)
        snackbar.view.translationY = -100F
        params.gravity = Gravity.BOTTOM
        snackbar.view.layoutParams = params
        snackbar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
        snackbar.show()



    }

    fun onClickBottom(view: View) {
        fragment?.visibility = View.VISIBLE
        val resImage = when(view.id)
        {
            R.id.imageButton1 -> R.drawable.ic_baseline_directions_bike_24
            R.id.imageButton2 -> R.drawable.ic_baseline_directions_run_24
            R.id.imageButton3 -> R.drawable.ic_baseline_drive_eta_24
            else -> R.drawable.shape_oval
        }
        findViewById<Button>(R.id.imageButton1).setTextColor(resources.getColor(R.color.primaryTextColor, theme))
        findViewById<Button>(R.id.imageButton2).setTextColor(resources.getColor(R.color.primaryTextColor, theme))
        findViewById<Button>(R.id.imageButton3).setTextColor(resources.getColor(R.color.primaryTextColor, theme))
        (view as Button).setTextColor(resources.getColor(R.color.secondaryColor, theme))

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, imageFragment.newInstance(resImage))
            .commit()


    }

    override fun onWordListClicked(){
        val item = WordList()
            .show(supportFragmentManager, "dialog")
    }

    override fun onEnglishTranslateClicked() {


    }

}