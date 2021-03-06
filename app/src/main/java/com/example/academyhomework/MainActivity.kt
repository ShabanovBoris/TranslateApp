package com.example.academyhomework

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.academyhomework.domain.features.simpleWordList.WordList
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), Router {


    var fragment :androidx.fragment.app.FragmentContainerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment = findViewById<androidx.fragment.app.FragmentContainerView>(R.id.main_container)

        lifecycle.currentState

    }



    var isHiden = true
    override fun onClickToHide(view: View) {

        if(isHiden) {
            isHiden = false
            view.rotation = 0F
            fragment?.visibility = View.GONE
            val snackbar =
                Snackbar.make(fragment!!.rootView, "You sure?", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Yep!") {
                        findViewById<Button>(R.id.imageButton1).visibility = View.VISIBLE
                        findViewById<Button>(R.id.imageButton2).visibility = View.VISIBLE
                        findViewById<Button>(R.id.imageButton3).visibility = View.VISIBLE
                    }.setActionTextColor(Color.BLACK)
            val params = FrameLayout.LayoutParams(snackbar.view.layoutParams)
            snackbar.view.translationY = -100F
            params.gravity = Gravity.BOTTOM
            snackbar.view.layoutParams = params
            snackbar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
            snackbar.show()
        }else{
            isHiden = true
            view.rotation = 180F
            fragment?.visibility = View.GONE
            findViewById<Button>(R.id.imageButton1).visibility = View.GONE
            findViewById<Button>(R.id.imageButton2).visibility = View.GONE
            findViewById<Button>(R.id.imageButton3).visibility = View.GONE
        }


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

    override fun onEnglishTranslateClicked(splashScreenfragment: SplashScreenfragment) {

        splashScreenfragment.findNavController().navigate(R.id.action_splashScreenfragment_to_quizFragment)

    }

    override fun onRussianTranslateClicked(splashScreenfragment: SplashScreenfragment) {
        splashScreenfragment.findNavController().navigate(R.id.action_splashScreenfragment_to_russianTranslateFragment)
    }

}