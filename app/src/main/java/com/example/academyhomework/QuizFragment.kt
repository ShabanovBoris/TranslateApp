package com.example.academyhomework

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.academyhomework.adapters.EnglishTranslateAdapter
import com.example.academyhomework.data.DataSource
import com.example.academyhomework.extensions.AnswerHandler
import com.example.academyhomework.extensions.toEnglishTranslateList
import com.example.academyhomework.model.EnglishTranslate

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class QuizFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var  recyclerView: RecyclerView
    private lateinit var list: MutableList<EnglishTranslate>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.rv_quiz)
        val listTemp = DataSource().loadWords(requireContext())
        list = (listTemp.toEnglishTranslateList()).toMutableList()
        var adapter =  EnglishTranslateAdapter()
        recyclerView.setHasFixedSize(true)
        adapter.bindList(list)
        adapter.setOnClickAnswerHandler(object : AnswerHandler {
            override val action: (EnglishTranslate.TranslateVariants, Int) -> Unit
                get() = { ans, pos ->
                    when (ans) {
                        EnglishTranslate.TranslateVariants.GOOD -> {
                            adapter.notifyItemRemoved(pos)
                            list.removeAt(pos)
                            adapter.bindList(list)
                        }

                        EnglishTranslate.TranslateVariants.WRONG -> {
                            Toast.makeText(
                                context,
                                "WRONG!",
                                Toast.LENGTH_SHORT
                            ).show()
                            adapter.notifyItemChanged(pos,"payload")
                        }
                    }
                }

            override fun handle(ans: EnglishTranslate.TranslateVariants, pos: Int) {
                action(ans, pos)

            }


        })
        recyclerView.adapter = adapter

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}