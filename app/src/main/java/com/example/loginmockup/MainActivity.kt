package com.example.loginmockup

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textViewUsernameLabel: TextView
    private lateinit var usernameLayout: TextInputLayout
    private lateinit var editTextUsername: TextInputEditText
    private lateinit var textViewPasswordLabel: TextView
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var buttonLogin: Button
    private lateinit var textViewMessage: TextView

    private val correctUsername = "lol1234"
    private val correctPassword = "passwd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        textViewUsernameLabel = findViewById(R.id.textViewUsernameLabel)
        usernameLayout = findViewById(R.id.usernameLayout)
        editTextUsername = findViewById(R.id.editTextUsername)
        textViewPasswordLabel = findViewById(R.id.textViewPasswordLabel)
        passwordLayout = findViewById(R.id.passwordLayout)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        textViewMessage = findViewById(R.id.textViewMessage)

        // Set onClick listener for the login button
        buttonLogin.setOnClickListener {
            if (validateInputs()) {
                if (authenticate()) {
                    showLoginSuccess()
                } else {
                    showLoginFailed()
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        val username = editTextUsername.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        // Validate username
        when {
            username.isEmpty() -> {
                usernameLayout.error = "Username cannot be empty"
                isValid = false
            }
            username.length < 4 -> {
                usernameLayout.error = "Username must be at least 4 characters"
                isValid = false
            }
            else -> usernameLayout.error = null
        }

        // Validate password
        when {
            password.isEmpty() -> {
                passwordLayout.error = "Password cannot be empty"
                isValid = false
            }
            password.length < 6 -> {
                passwordLayout.error = "Password must be at least 6 characters"
                isValid = false
            }
            else -> passwordLayout.error = null
        }

        return isValid
    }

    private fun authenticate(): Boolean {
        val username = editTextUsername.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        return username == correctUsername && password == correctPassword
    }

    private fun showLoginSuccess() {
        textViewMessage.text = "Login Successful"
        textViewMessage.setTextColor(resources.getColor(android.R.color.holo_green_dark, theme))
        textViewMessage.visibility = View.VISIBLE
        Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
    }

    private fun showLoginFailed() {
        textViewMessage.text = "Invalid username or password"
        textViewMessage.setTextColor(resources.getColor(android.R.color.holo_red_dark, theme))
        textViewMessage.visibility = View.VISIBLE
        Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
    }
}
