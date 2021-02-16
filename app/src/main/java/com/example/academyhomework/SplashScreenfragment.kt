package com.example.academyhomework

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.academyhomework.databinding.FragmentSplashScreenfragmentBinding
import com.example.academyhomework.databinding.FragmentWordListBinding
import com.example.academyhomework.extensions.Throughoutable

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

//    Below the binding property, create a property for the recycler view.
//    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.textViewAll.setOnClickListener{
            onAllListClick()
        }

        binding.buttonDown.apply {
            setOnClickListener { listener?.onClickToHide(findViewById<Button>(R.id.buttonDown))
                }

        }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_layout, menu)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return false // TODO: 12.02.2021  
        }
}