package com.example.smartscaleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DietPlan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_plan)


        val gainButton = findViewById<Button>(R.id.buttonGain)
        gainButton.setOnClickListener{
            val intent = Intent(this, DietPlanGain::class.java)
            startActivity(intent)
        }

        val loseButton = findViewById<Button>(R.id.buttonLose)
        loseButton.setOnClickListener{
            val intent = Intent(this, DietPlanLose::class.java)
            startActivity(intent)
        }
    }
}