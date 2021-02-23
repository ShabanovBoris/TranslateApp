package com.example.academyhomework

import android.os.Bundle
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
import kotlinx.coroutines.*

class QuizFragment : Fragment() {

    val scope = CoroutineScope(Dispatchers.Main)
    private lateinit var  recyclerView: RecyclerView
    private lateinit var list: MutableList<EnglishTranslate>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.rv_quiz)
        recyclerView.setHasFixedSize(true)
        scope.launch { setRecyclerList() }
    }

    private suspend fun setRecyclerList(){
        val listTemp = coroutineScope { DataSource().loadWords(requireContext()) }
        list = (listTemp.toEnglishTranslateList()).toMutableList()
        var adapter =  EnglishTranslateAdapter()

        adapter.setOnClickAnswerHandler(getHandler(adapter))

        adapter.bindList(list.shuffled())
        recyclerView.adapter = adapter
    }

    private fun getHandler(adapter: EnglishTranslateAdapter): AnswerHandler = object : AnswerHandler {
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
    }

}