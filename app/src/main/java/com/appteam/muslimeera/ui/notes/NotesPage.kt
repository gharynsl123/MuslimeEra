package com.appteam.muslimeera.ui.notes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appteam.muslimeera.data.local.Notes
import com.appteam.muslimeera.data.local.NotesViewModel
import com.appteam.muslimeera.databinding.ActivityNotesPageBinding
import com.appteam.muslimeera.until.HelperFunction

class NotesPage : AppCompatActivity(), HelperFunction.NotesClickDeleteInterface,
    HelperFunction.NotesClickInterface {
    private var _binding: ActivityNotesPageBinding? = null
    private val binding get() = _binding as ActivityNotesPageBinding

    private var _viewModel: NotesViewModel? = null
    private val viewModel get() = _viewModel as NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNotesPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mAdapter = NotesAdapter(this, this, this)

        _viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NotesViewModel::class.java]
        viewModel.allNotes.observe(this, Observer {
            it?.let {
                mAdapter.updateList(it)
            }
        })
        binding.apply {
            rvNotes.adapter = mAdapter
            rvNotes.layoutManager =
                GridLayoutManager(this@NotesPage, 2, RecyclerView.VERTICAL, false)
            rvNotes.setHasFixedSize(false)

            addNote.setOnClickListener {
                startActivity(Intent(this@NotesPage, AENotePage::class.java))
                finish()
            }
        }
    }

    override fun onDeleteIconClick(note: Notes) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClick(note: Notes) {
        val intent = Intent(this@NotesPage, AENotePage::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteID", note.id)
        startActivity(intent)
    }
}