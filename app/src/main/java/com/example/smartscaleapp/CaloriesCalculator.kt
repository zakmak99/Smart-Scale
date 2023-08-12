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



        val calculateButton = findViewById<Button>(R.id.buttonCalculate)
        calculateButton.setOnClickListener {
            calculateCalories()
        }


    }


    private fun calculateCalories(){

        val weight = findViewById<EditText>(R.id.editTextNumberWeight).text.toString().toDoubleOrNull()?:0.0
        val height = findViewById<EditText>(R.id.editTextNumberHeight).text.toString().toDoubleOrNull()?:0.0
        val age = findViewById<EditText>(R.id.editTextNumberAge).text.toString().toIntOrNull()?:0
        val sex = if (findViewById<RadioButton>(R.id.radioButtonMale).isChecked)"male" else "female"

        val dailyCalories = calculateCalories( sex, weight, height, age)

        val intent = Intent(this, CaloriesCalcResult::class.java)
        intent.putExtra("dailyCalories", dailyCalories)
        startActivity(intent)



    }

       private fun calculateCalories(sex: String, weightLb: Double, heightIn: Double, age: Int): Double{

       val bmr: Double


        if (sex == "male") {
            bmr = 66.5 + (6.23 * weightLb) + (12.7 * heightIn) - (6.8 * age)
        } else {
            bmr = 665 + (4.35 * weightLb) + (4.7 * heightIn) - (4.7 * age)
        }




           val activityLevel = 2.0

       return bmr * activityLevel
    }



}

