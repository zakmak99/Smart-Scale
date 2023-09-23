package com.example.smartscaleapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartUpDietPlan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_up_diet_plan)

        val dietPlanButton = findViewById<Button>(R.id.buttonDietPlan)
        dietPlanButton.setOnClickListener {
            val Intent = Intent(this, DietPlan::class.java)
            startActivity(Intent)
        }

        val allMealsButton = findViewById<Button>(R.id.buttonMeals)
        allMealsButton.setOnClickListener {
            val Intent = Intent(this, EveryMeal::class.java)
            startActivity(Intent)
        }
    }
}