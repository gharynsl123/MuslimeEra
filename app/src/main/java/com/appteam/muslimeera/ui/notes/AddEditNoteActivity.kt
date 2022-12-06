package com.appteam.muslimeera.ui.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.appteam.muslimeera.data.local.NotesViewModel
import com.appteam.muslimeera.databinding.ActivityAddEditNoteBinding

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

    }
}