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
            val intent = Intent(this, BMICalculator::class.java)
            startActivity(intent)
        }


        val caloriesCalButton = findViewById<Button>(R.id.buttonCaloriesCalc)
        caloriesCalButton.setOnClickListener{
            val intent = Intent(this, CaloriesCalculator::class.java)
            startActivity(intent)
        }


        val dietPlanButton = findViewById<Button>(R.id.buttonDietPlan)
        dietPlanButton.setOnClickListener{
            //val intent = intent(this, DietPlan::class.java)
            startActivity(intent)
        }

    }
}