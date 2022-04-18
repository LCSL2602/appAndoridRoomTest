package com.example.codelab_androidroom.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codelab_androidroom.R
import com.example.codelab_androidroom.database.entities.Word
import com.example.codelab_androidroom.databinding.WordItemBinding

class WordViewHolder(view:View):RecyclerView.ViewHolder(view) {
    private val binding = WordItemBinding.bind(view)
    companion object{
        fun create(parent:ViewGroup): WordViewHolder{
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.word_item,parent,false)
            return WordViewHolder(view)
        }
    }

    fun render(word: Word, deleteWord:(Word)->Unit) {
        binding.textView.text = word.word
        binding.btnDelete.setOnClickListener {
            deleteWord(word)
        }
    }


}