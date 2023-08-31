package com.example.smartscaleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DietPlan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_plan)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val age = findViewById<EditText>(R.id.ageEditText).text.toString()
            val goal = findViewById<EditText>(R.id.goalEditText).text.toString()
            val activityLevel = findViewById<EditText>(R.id.activityLevelEditText).text.toString()
            val mealsPerDay = findViewById<EditText>(R.id.mealsPerDayEditText).text.toString()

            // Validate inputs and handle missing values
            if (age.isBlank() || goal.isBlank() || activityLevel.isBlank() || mealsPerDay.isBlank()) {
                // Show an error message to the user
                Toast.makeText(this, "Please fill in all the required fields.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Additional questions logic and decision-making based on inputs
            if (age.isNotBlank() && goal.isNotBlank() && activityLevel.isNotBlank() && mealsPerDay.isNotBlank()) {
                val intent = when {
                    goal.equals("gain", ignoreCase = true) -> Intent(this, DietPlanGain::class.java)
                    goal.equals("lose", ignoreCase = true) -> Intent(this, DietPlanLose::class.java)
                    // Handle other cases or show an error
                    else -> null
                }

                intent?.let {
                    startActivity(intent)
                }
            }
        }
    }
}