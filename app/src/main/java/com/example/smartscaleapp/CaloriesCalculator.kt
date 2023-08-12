package com.example.smartscaleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Button
import android.widget.TextView

class CaloriesCalculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories_calculator)

        val nextButton = findViewById<Button>(R.id.buttonNext)
        nextButton.setOnClickListener{
            val intent = Intent(this, CaloriesCalcResult::class.java)
            startActivity(intent)
        }

    }


    private fun calculateCalories(){

        val weight = findViewById<EditText>(R.id.editTextNumberWeight).text.toString().toDoubleOrNull()?:0.0
        val height = findViewById<EditText>(R.id.editTextNumberHeight).text.toString().toDoubleOrNull()?:0.0
        val age = findViewById<EditText>(R.id.editTextNumberAge).text.toString().toIntOrNull()?:0
        val sex = if (findViewById<RadioButton>(R.id.radioButtonMale).isChecked)"male" else "female"

        val dailyCalories = calculateCalories( sex, weight, height, age)


    }

       private fun calculateCalories(sex: String, weightKg: Double, heightCm: Double, age: Int): Double{

       val bmr: Double

        if (sex == "male") {
            bmr = 88.362 + (13.397 * weightKg) + (4.799 * heightCm) - (5.677 * age)
        } else {
            bmr = 447.593 + (9.247 * weightKg) + (3.098 * heightCm) - (4.330 * age)
        }


       return bmr
    }



}

