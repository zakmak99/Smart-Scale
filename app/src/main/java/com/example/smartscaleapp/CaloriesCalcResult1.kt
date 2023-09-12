package com.example.smartscaleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class CaloriesCalcResult1 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calories_calc_result1, container, false)

        val dailyCalories = arguments?.getDouble("calories") ?: 0.0

        val resultTextView = view.findViewById<TextView>(R.id.textViewResult)
        resultTextView.text = "Daily Calories: $dailyCalories"

        return view
    }

}