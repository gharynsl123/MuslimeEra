package com.appteam.muslimeera.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.appteam.muslimeera.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private var _binding: ActivityProfileBinding? = null
    private val binding get() = _binding as ActivityProfileBinding

    private var _auth : FirebaseAuth? = null
    private val auth get() = _auth as FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        _auth = FirebaseAuth.getInstance()

        binding.apply {
            btnLogout.setOnClickListener {
                auth.signOut()
                Intent(this@ProfileActivity, LoginActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    Toast.makeText(this@ProfileActivity, "Logout Berhasil", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}