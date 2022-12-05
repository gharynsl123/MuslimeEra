package com.appteam.muslimeera.ui.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appteam.muslimeera.R
import com.appteam.muslimeera.databinding.ActivityNotesPageBinding

class NotesPage : AppCompatActivity() {
    var _binding: ActivityNotesPageBinding? = null
    val binding get() = _binding as ActivityNotesPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNotesPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}