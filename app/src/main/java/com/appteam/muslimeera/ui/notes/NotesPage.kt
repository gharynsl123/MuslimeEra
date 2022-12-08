package com.appteam.muslimeera.ui.notes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.appteam.muslimeera.R
import com.appteam.muslimeera.data.local.Notes
import com.appteam.muslimeera.data.local.NotesViewModel
import com.appteam.muslimeera.databinding.ActivityNotesPageBinding
import com.appteam.muslimeera.ui.main.MainActivity
import com.appteam.muslimeera.until.HelperFunction
import com.google.android.material.snackbar.Snackbar

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

        supportActionBar?.hide()

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
            rvNotes.apply {
                adapter = mAdapter
                layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                setHasFixedSize(false)
                swipeToDelete(this)
            }

            addNote.setOnClickListener {
                startActivity(Intent(this@NotesPage, AENotePage::class.java))
            }

            btnBack.setOnClickListener {
                startActivity(Intent(this@NotesPage, MainActivity::class.java))
                finish()
            }
        }
    }

    override fun onDeleteIconClick(note: Notes) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note: Notes) {
        val intent = Intent(this@NotesPage, AENotePage::class.java)
        intent.apply {
            putExtra("noteType", "Edit")
            putExtra("noteTitle", note.noteTitle)
            intent.putExtra("noteDescription", note.noteDescription)
            intent.putExtra("noteID", note.id)
        }
        startActivity(intent)
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDelete = object :
            ItemTouchHelper.SimpleCallback(// ItemTouchHelper supaya bisa menggeser kekiri atau kekanan
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem = NotesAdapter(this@NotesPage, this@NotesPage, this@NotesPage).allNotes[viewHolder.adapterPosition]
                viewModel.deleteNote(deletedItem)
                restorData(viewHolder.itemView, deletedItem)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun restorData(view: View, item: Notes) {
        val snackBar = Snackbar.make(
            view, "Deleted: '${item.noteTitle}'",
            Snackbar.LENGTH_LONG
        )
        snackBar.setTextColor(ContextCompat.getColor(view.context, R.color.black))
        snackBar.setAction("Undo") {
            viewModel.insertData(item)
        }
        snackBar.show()
    }
}