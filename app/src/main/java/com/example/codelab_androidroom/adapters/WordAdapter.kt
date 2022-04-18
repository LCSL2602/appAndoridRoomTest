package com.example.codelab_androidroom.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.codelab_androidroom.database.entities.Word

class WordAdapter(private val deleteWord:(Word) -> Unit ): ListAdapter<Word,WordViewHolder>(WordsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
      return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = getItem(position)
        holder.render(item, deleteWord )
    }

}

class WordsComparator : DiffUtil.ItemCallback<Word>() {
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.word == newItem.word
    }

}
