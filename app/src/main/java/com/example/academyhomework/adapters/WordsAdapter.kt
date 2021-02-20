package com.example.academyhomework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.academyhomework.R
import com.example.academyhomework.model.Dword
import java.lang.IllegalArgumentException


class WordsAdapter():RecyclerView.Adapter<WordsAdapter.WordsItemViewHolder>() {


    private var words = listOf<Dword>()

    abstract class WordsItemViewHolder(view: View): RecyclerView.ViewHolder(view)
    class HeaderViewHolder(view: View): WordsItemViewHolder(view)
    class WordViewHolder(view: View): WordsItemViewHolder(view){
        val textViewWord = view.findViewById<TextView>(R.id.textViewWord)
        val textViewTranslate: TextView = view.findViewById(R.id.textViewTranslate)

        fun bindWordFields(wordItem: Dword){
            textViewWord.text = wordItem.word
            textViewTranslate.text = wordItem.translate
        }
    }
    fun bindWords(list: List<Dword>){
        words = list
        notifyDataSetChanged()
    }

    enum class ViewTypes(type:Int){
        HEADER(0),
        WORD(1)
    }

    override fun getItemViewType(position: Int): Int {
        return if(words[position].word == "nope") {ViewTypes.HEADER.ordinal}
        else ViewTypes.WORD.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsItemViewHolder {
        return when (viewType) {

            ViewTypes.WORD.ordinal -> WordViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.words_item, parent, false))

            ViewTypes.HEADER.ordinal ->  HeaderViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.words_header, parent, false))

            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: WordsItemViewHolder, position: Int) {

        when(holder) {
            is WordViewHolder -> holder.bindWordFields(words[position])
        }
    }

    override fun getItemCount(): Int {
        return words.size +1
    }
}




