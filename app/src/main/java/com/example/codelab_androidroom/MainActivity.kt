package com.example.codelab_androidroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codelab_androidroom.adapters.WordAdapter
import com.example.codelab_androidroom.database.entities.Word
import com.example.codelab_androidroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val newWordActivityRequestCode = 1
    private lateinit var binding:ActivityMainBinding
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WordAdapter { wordViewModel.delete(it) }
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let {
                adapter.submitList(it)
            }
        })

        binding.fab.setOnClickListener{
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent,newWordActivityRequestCode)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let {
                val word = Word(word = it, id = 0)
                wordViewModel.insert(word)
            }
        }else{
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}