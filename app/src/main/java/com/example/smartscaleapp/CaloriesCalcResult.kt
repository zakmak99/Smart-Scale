package com.example.smartscaleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Button
import android.widget.TextView

class CaloriesCalcResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories_calc_result)

        val resultTextView = findViewById<TextView>(R.id.textView)

        val dailyCalories = intent.getDoubleExtra("dailyCalories", 0.7)
        resultTextView.text = "Daily calories needed: $dailyCalories"
    }
}