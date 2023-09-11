package com.example.smartscaleapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore

class DisplayNameActivity : AppCompatActivity() {

    private lateinit var displayNameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_name)

        displayNameEditText = findViewById(R.id.displayNameEditText)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)

        saveButton.setOnClickListener {
            val displayName = displayNameEditText.text.toString().trim()

            if (displayName.isNotEmpty()) {
                // Update Firebase Authentication with the new display name
                val user = FirebaseAuth.getInstance().currentUser
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .build()

                user?.updateProfile(profileUpdates)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Display name updated successfully
                        // You can navigate back to the main activity or perform other actions
                        finish()
                    } else {
                        // Handle the update error
                        // Show a message to the user, e.g., "Failed to update display name"
                    }
                }
            }
        }
        saveButton.setOnClickListener {
            val displayName = displayNameEditText.text.toString().trim()

            if (displayName.isNotEmpty()) {
                // Update Firebase Authentication with the new display name
                val user = FirebaseAuth.getInstance().currentUser
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .build()

                user?.updateProfile(profileUpdates)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Display name updated successfully

                        // Create an intent to return the display name as a result
                        val resultIntent = Intent()
                        resultIntent.putExtra("display_name", displayName)

                        // Set the result code and the intent with the data
                        setResult(Activity.RESULT_OK, resultIntent)

                        // Finish the activity to return to the calling activity (LoginActivity)
                        finish()
                    } else {
                        // Handle the update error
                        // Show a message to the user, e.g., "Failed to update display name"
                    }
                }
            }
        }

        cancelButton.setOnClickListener {
            // Handle the cancel button action, e.g., navigate back to the previous screen
            finish()
        }
    }
    private fun updateDisplayNameInFirestore(displayName: String) {
        // Get the current user's UID from FirebaseAuth
        val uid = FirebaseAuth.getInstance().currentUser?.uid

        if (uid != null) {
            // Reference to your Firestore collection for user profiles
            val db = FirebaseFirestore.getInstance()
            val userRef = db.collection("users").document(uid)

            // Update the user's display name in Firestore
            userRef.update("displayName", displayName)
                .addOnSuccessListener {
                    // Display name updated successfully in Firestore
                    // You can handle this success event as needed
                }
                .addOnFailureListener { e ->
                    // Handle the failure to update display name in Firestore
                    // You can log an error or show a message to the user
                }
        }
        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("displayName", displayName)
        editor.apply()
        Log.d("DisplayNameActivity", "Display name saved: $displayName")

    }

}
// Handl