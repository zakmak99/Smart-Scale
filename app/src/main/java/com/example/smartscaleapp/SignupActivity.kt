package com.example.smartscaleapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        val signUpButton = findViewById<Button>(R.id.signupBtn)
        signUpButton.setOnClickListener {
            signUpWithEmail()
        }
    }

    private fun signUpWithEmail() {
        val emailEditText = findViewById<EditText>(R.id.signupEmailEditText)
        val passwordEditText = findViewById<EditText>(R.id.signupPasswordEditText)

        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Sign-up successful!", Toast.LENGTH_SHORT).show()
                    // You can handle the successful sign-up here
                    finish() // Finish the activity after successful signup
                } else {
                    Toast.makeText(this, "Sign-up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
} // done
