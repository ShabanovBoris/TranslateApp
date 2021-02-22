package com.example.academyhomework.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.academyhomework.R
import com.example.academyhomework.extensions.AnswerHandler
import com.example.academyhomework.model.EnglishTranslate
import com.google.android.material.button.MaterialButton

class EnglishTranslateAdapter() : RecyclerView.Adapter<EnglishTranslateAdapter.ETViewHolder>() {

    private var list = listOf<EnglishTranslate>()
    private var answerHandler: AnswerHandler? = null

    fun setOnClickAnswerHandler(handler: AnswerHandler) {
        answerHandler = handler
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position].engWord) {
            "nope" -> TYPE.EMPTY.ordinal
            else -> TYPE.WORD.ordinal
        }
    }


    abstract class ETViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    private class EmptyETViewHolder(itemView: View) : ETViewHolder(itemView)
    private class WordETViewHolder(itemView: View, answerHandler: AnswerHandler?) :
        ETViewHolder(itemView), View.OnClickListener {
        val answerHandler = answerHandler

        val word: TextView = itemView.findViewById(R.id.tv_word)

        val b1: MaterialButton = itemView.findViewById(R.id.b_translate_1)
        val b2: MaterialButton = itemView.findViewById(R.id.b_translate_2)
        val b3: MaterialButton = itemView.findViewById(R.id.b_translate_3)
        val b4: MaterialButton = itemView.findViewById(R.id.b_translate_4)

        var listNested = listOf<EnglishTranslate>()

        fun bindAnswers(item: EnglishTranslate, list: List<EnglishTranslate>) {
            item.setAnswers(list, itemView)
            word.text = item.engWord
            listNested = list
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.b_translate_1, R.id.b_translate_2, R.id.b_translate_3, R.id.b_translate_4
                -> if (listNested[adapterPosition].translateRus == (v as MaterialButton).text) {
                    answerHandler?.handle(EnglishTranslate.TranslateVariants.GOOD,adapterPosition)
                } else {
                    answerHandler?.handle(EnglishTranslate.TranslateVariants.WRONG,adapterPosition) }


            }
            //(v as MaterialButton).text
        }
    }


    fun bindList(listT: List<EnglishTranslate>) {
        list = listT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ETViewHolder {
        return when (viewType) {
            TYPE.WORD.ordinal ->
                WordETViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.quiz_view_holder, parent, false),
                    answerHandler
                )
            else -> EmptyETViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.empty_word_item, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ETViewHolder, position: Int, payload: MutableList<Any>) {
        when (holder) {
            is WordETViewHolder -> {
                if(payload.contains("payload")){
                    holder.itemView.alpha = 0.5F
                }else {
                    holder.itemView.alpha = 1F
                    holder.bindAnswers(list[position], list)
                    holder.b1.setOnClickListener(holder)
                    holder.b2.setOnClickListener(holder)
                    holder.b3.setOnClickListener(holder)
                    holder.b4.setOnClickListener(holder)
                }

            }

        }

    }
    override fun onBindViewHolder(holder: ETViewHolder, position: Int) {
        when (holder) {
            is WordETViewHolder -> {

                holder.bindAnswers(list[position], list)
                holder.b1.setOnClickListener(holder)
                holder.b2.setOnClickListener(holder)
                holder.b3.setOnClickListener(holder)
                holder.b4.setOnClickListener(holder)

            }

        }
    }

    override fun getItemCount(): Int = list.size

    enum class TYPE(int: Int) {
        EMPTY(0),
        WORD(1)
    }



}