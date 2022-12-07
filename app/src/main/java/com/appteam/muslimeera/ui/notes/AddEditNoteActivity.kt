package com.appteam.muslimeera.ui.notes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.appteam.muslimeera.R
import com.appteam.muslimeera.data.local.Notes
import com.appteam.muslimeera.data.local.NotesViewModel
import com.appteam.muslimeera.databinding.ActivityAddEditNoteBinding
import com.appteam.muslimeera.ui.main.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    private var _binding: ActivityAddEditNoteBinding? = null
    private val binding get() = _binding as ActivityAddEditNoteBinding

    var noteID = -1

    private var _viewModel : NotesViewModel? = null
    private val viewModel get() = _viewModel as NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NotesViewModel::class.java]

        val noteType = intent.getStringArrayExtra("noteType")
        if (noteType?.equals("Edit") == true){
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteID", -1)
            binding.btnAddUpdate.setImageResource(R.drawable.ic_update)
            binding.edtNotesTitle.setText(noteTitle)
            binding.edtNotesDescription.setText(noteDesc)
        }else{
            binding.btnAddUpdate.setImageResource(R.drawable.ic_add)
        }

        binding.btnAddUpdate.setOnClickListener {
            val noteTitle = binding.edtNotesTitle.text.toString()
            val noteDescription = binding.edtNotesDescription.text.toString()

            if (noteType?.equals("Edit") == true){
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate :String = sdf.format(Date())
                    val updateNote = Notes(noteTitle, noteDescription, currentDate)
                    updateNote.id = noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Update...", Toast.LENGTH_SHORT).show()
                }
            }else{
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDate :String = sdf.format(Date())
                viewModel.addNote(Notes(noteTitle, noteDescription, currentDate))
                Toast.makeText(this, "Note Added...", Toast.LENGTH_SHORT).show()
            }
        }
        startActivity(Intent(applicationContext , MainActivity::class.java))
        this.finish()
    }
}