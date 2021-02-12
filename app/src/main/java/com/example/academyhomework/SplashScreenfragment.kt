package com.example.academyhomework

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.academyhomework.databinding.FragmentSplashScreenfragmentBinding
import com.example.academyhomework.databinding.FragmentWordListBinding

class SplashScreenfragment : Fragment() {

    private var _binding: FragmentSplashScreenfragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSplashScreenfragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

//    Below the binding property, create a property for the recycler view.
//    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.textViewAll.setOnClickListener{
            onAllListClick()
        }

    }

    private fun onAllListClick() {
        val action = SplashScreenfragmentDirections.actionSplashScreenfragmentToWordList(true)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_layout, menu)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return false // TODO: 12.02.2021  
        }
}