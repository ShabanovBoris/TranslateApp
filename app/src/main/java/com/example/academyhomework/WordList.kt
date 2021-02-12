package com.example.academyhomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.academyhomework.adapters.WordsAdapter
import com.example.academyhomework.data.DataSource


class WordList : Fragment() {

   private  lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_word_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            recyclerView = view.findViewById(R.id.word_recycler)
            recyclerView.adapter = WordsAdapter(
                DataSource().loadWords(context?.applicationContext!!)
            )
    }


}