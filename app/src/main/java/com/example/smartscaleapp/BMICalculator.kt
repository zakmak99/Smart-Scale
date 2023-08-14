package com.example.smartscaleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup

class BMICalculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmicalculator)


        val calculateButton = findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        val weightEditText = findViewById<EditText>(R.id.weightEditText)
        val heightEditText = findViewById<EditText>(R.id.heightEditText)
        val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)

        val weight = weightEditText.text.toString().toFloat()
        val height = heightEditText.text.toString().toFloat() / 100 // Convert cm to m
        val gender = if (genderRadioGroup.checkedRadioButtonId == R.id.maleRadioButton) "male" else "female"

        val bmi = weight / (height * height)

        val resultText = when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Normal Weight"
            bmi < 29.9 -> "Overweight"
            else -> "Obese"
        }

        val intent = Intent(this, BMIResults::class.java)
        intent.putExtra("result", resultText)
        startActivity(intent)
    }
}
