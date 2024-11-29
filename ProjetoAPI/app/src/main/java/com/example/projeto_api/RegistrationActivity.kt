package com.example.projeto_api

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.security.MessageDigest

class RegistrationActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "UserPrefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        val usernameInput = findViewById<TextInputEditText>(R.id.usernameInputRegister)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInputRegister)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else if (username.contains(" ")) {
                Toast.makeText(this, "O nome de usuário não pode conter espaços!", Toast.LENGTH_SHORT).show()
            } else {
                val hashedPassword = hashPassword(password)
                val editor = sharedPreferences.edit()
                editor.putString(username, hashedPassword)
                editor.apply()

                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun hashPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
