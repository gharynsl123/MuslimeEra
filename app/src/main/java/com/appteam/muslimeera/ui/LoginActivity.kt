package com.appteam.muslimeera.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.appteam.muslimeera.R
import com.appteam.muslimeera.databinding.ActivityLoginBinding
import com.appteam.muslimeera.ui.main.MainActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding as ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.apply {

            val usernameStream = RxTextView.textChanges(etEmail)
                .skipInitialValue()
                .map {
                    it.isEmpty()
                }
            usernameStream.subscribe {
                textMinimal(it, "userName")
            }
            val passwordStream = RxTextView.textChanges(etPass)
                .skipInitialValue()
                .map {
                    it.isEmpty()
                }
            passwordStream.subscribe {
                textMinimal(it, "password")
            }
            val invalidStream = Observable.combineLatest(
                usernameStream,
                passwordStream,
            ) { usernameInvalid: Boolean,
                passwordInvalid: Boolean ->
                !usernameInvalid && !passwordInvalid
            }
            invalidStream.subscribe {
                if (it) {
                    llButonLogin.isEnabled = true
                    llButonLogin.background =
                        ContextCompat.getDrawable(this@LoginActivity, R.drawable.btn_back_img)
                } else {
                    llButonLogin.isEnabled = false
                }
            }

            llButonLogin.setOnClickListener {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
            tvsigin.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }

    private fun textMinimal(notValid: Boolean, text: String) {
        if (text == "Username/Email") {
            binding.etEmail.error = if (notValid) "$text Tidak Boleh Kosong" else null
        } else if (text == "password") {
            binding.etPass.error = if (notValid) "$text Tidak Boleh Kosong" else null
        }
    }

}