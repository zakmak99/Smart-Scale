package com.example.smartscaleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FitnessPlan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fitness_plan)


        val gainButton = findViewById<Button>(R.id.buttonGain)
        gainButton.setOnClickListener{
            val Intent = Intent(this, FitnessplanGain::class.java)
            startActivity(Intent)
        }

        val loseButton = findViewById<Button>(R.id.buttonLose)
        loseButton.setOnClickListener{
            val Intent = Intent(this, FitnessplanLose::class.java)
            startActivity(Intent)
        }
    }
}