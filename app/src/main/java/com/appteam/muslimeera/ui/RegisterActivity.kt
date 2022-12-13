package com.appteam.muslimeera.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.appteam.muslimeera.R
import com.appteam.muslimeera.databinding.ActivityRegisterBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable

class RegisterActivity : AppCompatActivity() {
    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding as ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.apply {
            val nameStream = RxTextView.textChanges(etFullName)
                .skipInitialValue()
                .map {
                    it.isEmpty()
                }
            nameStream.subscribe {
                showNameExist(it)
            }

            val emailStream = RxTextView.textChanges(etEmail)
                .skipInitialValue()
                .map {
                    !Patterns.EMAIL_ADDRESS.matcher(it).matches()
                }
            emailStream.subscribe {
                emailValid(it)
            }

            val usernameStream = RxTextView.textChanges(etUserName)
                .skipInitialValue()
                .map {
                    it.length < 3
                }
            usernameStream.subscribe {
                textMinimal(it, "userName")
            }
            val passwordStream = RxTextView.textChanges(etPass)
                .skipInitialValue()
                .map {
                    it.length < 8
                }
            passwordStream.subscribe {
                textMinimal(it, "password")
            }

            val passwordConfirmStream = Observable.merge(
                RxTextView.textChanges(etPass)
                    .skipInitialValue()
                    .map {
                        it.toString() != etConfirmPassword.text.toString()
                    },
                RxTextView.textChanges(etConfirmPassword)
                    .skipInitialValue()
                    .map {
                        it.toString() != etPass.text.toString()
                    })

            passwordConfirmStream.subscribe {
                passwordConfirm(it)
            }

            val invalidStream = Observable.combineLatest(
                nameStream,
                emailStream,
                usernameStream,
                passwordStream,
                passwordConfirmStream
            ) { nameInvalid: Boolean, emailInvalid: Boolean,
                usernameInvalid: Boolean,
                passwordInvalid: Boolean,
                passwordConfirmInvalid: Boolean ->
                !nameInvalid && !emailInvalid && !usernameInvalid && !passwordInvalid && !passwordConfirmInvalid
            }
            invalidStream.subscribe{
                if (it){
                    llButonRegister.isEnabled = true
                    llButonRegister.background = ContextCompat.getDrawable(this@RegisterActivity, R.drawable.btn_back_img)
                }else{
                    llButonRegister.isEnabled = false
                }
            }

            llButonRegister.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
            tvlogin.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }
    }

    private fun showNameExist(notValid: Boolean) {
        binding.etFullName.error = if (notValid) "Nama Tidak Boleh Kosong" else null
    }

    private fun textMinimal(notValid: Boolean, text: String) {
        if (text == "Username") {
            binding.etUserName.error = if (notValid) "$text Harus Lebih Dari 3 huruf" else null
        } else if (text == "password") {
            binding.etPass.error = if (notValid) "$text Harus Lebih Dari 8 huruf" else null
        }
    }

    private fun emailValid(notValid: Boolean) {
        binding.etEmail.error = if (notValid) "Email Tidak Valid" else null
    }

    private fun passwordConfirm(notValid: Boolean) {
        binding.etConfirmPassword.error = if (notValid) "Password Tidak Sama" else null
    }
}