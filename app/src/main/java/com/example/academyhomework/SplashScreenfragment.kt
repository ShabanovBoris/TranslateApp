package com.example.academyhomework

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.academyhomework.databinding.FragmentSplashScreenfragmentBinding
import com.example.academyhomework.databinding.FragmentWordListBinding
import com.example.academyhomework.extensions.Throughoutable
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SplashScreenfragment : Fragment() {

    private var _binding: FragmentSplashScreenfragmentBinding? = null
    private val binding get() = _binding!!

    private var listener:Throughoutable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Throughoutable){
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSplashScreenfragmentBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.textViewAll.setOnClickListener{
            onAllListClick()
        }

        binding.buttonDown.apply {
            setOnClickListener { listener?.onClickToHide(findViewById<Button>(this.id))
                }

        }

        binding.tvER.apply { setOnClickListener{
            listener?.onEnglishTranslateClicked()

            findNavController().navigate(R.id.action_splashScreenfragment_to_quizFragment)} }

    }

    private fun onAllListClick() {
//        val action = SplashScreenfragmentDirections.actionSplashScreenfragmentToWordList(true)
//        findNavController().navigate(action)
        listener?.onWordListClicked()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}