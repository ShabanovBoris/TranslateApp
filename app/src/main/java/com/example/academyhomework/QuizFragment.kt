package com.example.academyhomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.academyhomework.adapters.EnglishTranslateAdapter
import com.example.academyhomework.data.DataSource
import com.example.academyhomework.extensions.AnswerHandler
import com.example.academyhomework.extensions.toEnglishTranslateList
import com.example.academyhomework.model.EnglishTranslate
import kotlinx.coroutines.*

class QuizFragment : Fragment(R.layout.fragment_quiz) {

    val scope = CoroutineScope(Dispatchers.Main)
    private lateinit var  recyclerView: RecyclerView
    private lateinit var timerView: TextView
    private lateinit var list: MutableList<EnglishTranslate>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        timerView = view.findViewById<TextView>(R.id.tv_timer)
        timerView.text = "0"
        recyclerView = view.findViewById(R.id.rv_quiz)
        recyclerView.setHasFixedSize(true)
        scope.launch { setRecyclerList() }
    }

    private suspend fun setRecyclerList(){
        val listTemp = coroutineScope { DataSource().loadWords(requireContext()) }
        list = (listTemp.toEnglishTranslateList()).toMutableList()
        val adapter =  EnglishTranslateAdapter()

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

        override fun handle(ans: EnglishTranslate.TranslateVariants, adapterPosition: Int) {
            action(ans, adapterPosition)
        }
    }

    override fun onStart() {
        super.onStart()
        scope.launch { timerStart() }
    }

    private suspend fun timerStart() = withContext(Dispatchers.IO) {
        while (true) {
            delay(1000L)
            timerView.apply {
                withContext(Dispatchers.Main){text = (text.toString().toInt() + 1).toString()}
            }
        }
    }

    override fun onStop() {
        super.onStop()
        scope.cancel()
    }

}