package com.example.academyhomework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.academyhomework.R
import com.example.academyhomework.model.Dword


class WordsAdapter( private val dataset:List<Dword>):RecyclerView.Adapter<WordsAdapter.WordsItemViewHolder>() {

    class WordsItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textViewWord = view.findViewById<TextView>(R.id.textViewWord)
        val textViewTranslate: TextView = view.findViewById(R.id.textViewTranslate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.words_item,parent,false)
        return WordsItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: WordsItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.textViewWord.text = item.word
        holder.textViewTranslate.text = item.translate
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}