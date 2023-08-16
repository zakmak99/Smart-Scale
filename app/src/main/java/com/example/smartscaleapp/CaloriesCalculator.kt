package com.example.smartscaleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode
import android.widget.RadioGroup

class CaloriesCalculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories_calculator)

        val calculateButton = findViewById<Button>(R.id.buttonCalculate)
        calculateButton.setOnClickListener {
            if(areFieldsValid()){
                calculateCalories()
            }
            else{
                Toast.makeText(this, "Please complete all the fields", Toast.LENGTH_SHORT).show()
            }
        }

    }



    private fun areFieldsValid():Boolean{
        val weightText = findViewById<EditText>(R.id.editTextNumberWeight).text.toString()
        val heightText = findViewById<EditText>(R.id.editTextNumberHeight).text.toString()
        val ageText = findViewById<EditText>(R.id.editTextNumberAge).text.toString()

        return weightText.isNotBlank() && heightText.isNotBlank() && ageText.isNotBlank()
    }



    private fun calculateCalories(){

        val weight = findViewById<EditText>(R.id.editTextNumberWeight).text.toString().toDoubleOrNull()?:0.0
        val height = findViewById<EditText>(R.id.editTextNumberHeight).text.toString().toDoubleOrNull()?:0.0
        val age = findViewById<EditText>(R.id.editTextNumberAge).text.toString().toIntOrNull()?:0

        val radioGroupGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        val selectedGenderId = radioGroupGender.checkedRadioButtonId
        val sex = if (selectedGenderId == R.id.radioButtonMale) "male" else "female"


        val dailyCalories = calculateCalories( sex, weight, height, age)

        val intent = Intent(this, CaloriesCalcResult::class.java)
        intent.putExtra("dailyCalories", dailyCalories)
        startActivity(intent)

    }



       private fun calculateCalories(sex: String, weightKg: Double, heightCm: Double, age: Int): Double{

       val bmr: Double

        if (sex == "male") {
            bmr = 88.362 + (13.397 * weightKg) + (4.799 * heightCm) - (5.677 * age)
        } else {
            bmr = 447.593 + (9.247 * weightKg) + (3.098 * heightCm) - (4.330 * age)
        }


           val activityLevel = 1.0

           val calculatedCalories = bmr * activityLevel

           val roundedCalories = BigDecimal(calculatedCalories).setScale(2, RoundingMode.HALF_EVEN).toDouble()


           return roundedCalories
    }



}

