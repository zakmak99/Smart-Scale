package com.example.smartscaleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bmiCalcButton = findViewById<Button>(R.id.buttonBMICalc)
        bmiCalcButton.setOnClickListener{
            val Intent = Intent(this, BMICalculator::class.java)
            startActivity(Intent)
        }


        val caloriesCalButton = findViewById<Button>(R.id.buttonCaloriesCalc)
        caloriesCalButton.setOnClickListener{
            val Intent = Intent(this, CaloriesCalculator::class.java)
            startActivity(Intent)
        }


        val dietPlanButton = findViewById<Button>(R.id.buttonDietPlan)
        dietPlanButton.setOnClickListener{
            val Intent = Intent(this, DietPlan::class.java)
            startActivity(Intent)
        }
    }
}