package com.appteam.muslimeera.ui.notes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.appteam.muslimeera.R
import com.appteam.muslimeera.data.local.Notes
import com.appteam.muslimeera.data.local.NotesViewModel
import com.appteam.muslimeera.databinding.ActivityAenotePageBinding
import java.text.SimpleDateFormat
import java.util.*

class AENotePage : AppCompatActivity() {


    private var _binding: ActivityAenotePageBinding? = null
    private val binding get() = _binding as ActivityAenotePageBinding

    var noteID = -1

    private var _viewModel: NotesViewModel? = null
    private val viewModel get() = _viewModel as NotesViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAenotePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NotesViewModel::class.java]

        val noteType = intent.getStringExtra("noteType")

        binding.apply {

            if (noteType.equals("Edit")) {

                val noteTitle = intent.getStringExtra("noteTitle")
                val noteDesc = intent.getStringExtra("noteDescription")

                noteID = intent.getIntExtra("noteID", -1)

                btnAddUpdate.setImageResource(R.drawable.ic_update)
                edtNotesTitle.setText(noteTitle)
                edtNotesDescription.setText(noteDesc)
            } else {
                btnAddUpdate.setImageResource(R.drawable.ic_add)
            }

            btnAddUpdate.setOnClickListener {
                val noteTitle = edtNotesTitle.text.toString()
                val noteDescription = edtNotesDescription.text.toString()

                when {
                    edtNotesTitle.text.isEmpty() -> {
                        edtNotesTitle.error = "Please Fill Field"
                    }
                    edtNotesDescription.text.isEmpty() -> {
                        Toast.makeText(this@AENotePage, "Your Notes Is still empty", Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        if (noteType?.equals("Edit") == true) {
                            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                                val currentDate: String = sdf.format(Date())
                                val updateNote = Notes(noteTitle, noteDescription, currentDate)
                                updateNote.id = noteID
                                viewModel.updateNote(updateNote)
                                Toast.makeText(this@AENotePage, "Note Update...", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                            val currentDate: String = sdf.format(Date())
                            viewModel.addNote(Notes(noteTitle, noteDescription, currentDate))
                            Toast.makeText(this@AENotePage, "Note Added...", Toast.LENGTH_LONG).show()
                        }
                        startActivity(Intent(this@AENotePage, NotesPage::class.java))
                        this@AENotePage.finish()
                    }
                }
            }
            btnCancel.setOnClickListener {
                Toast.makeText(this@AENotePage, "Noted Canceled", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@AENotePage, NotesPage::class.java))
                finish()
            }

        }//END OF BINDING

    }//END OF ON CREATE
}