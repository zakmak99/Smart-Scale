package com.example.smartscaleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BMIResults : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiresults)

        val resultText = intent.getStringExtra("result")

        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        resultTextView.text = resultText
    }
}
