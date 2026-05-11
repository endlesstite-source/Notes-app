package com.example.notesapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val notes = mutableListOf<String>()
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NoteAdapter(notes) { position ->
            notes.removeAt(position)
            adapter.notifyItemRemoved(position)
            Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show()
        }
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = adapter

        binding.addButton.setOnClickListener {
            val text = binding.noteInput.text.toString().trim()
            if (text.isNotEmpty()) {
                notes.add(text)
                adapter.notifyItemInserted(notes.size - 1)
                binding.noteInput.text.clear()
                binding.notesRecyclerView.smoothScrollToPosition(notes.size - 1)
            }
        }
    }
}
