package com.example.smartscaleapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import java.math.BigDecimal
import java.math.RoundingMode


class CaloriesCalcResult1 : Fragment() {
    private val activityLevelMultiplierMap = mapOf(
        "Sedentary" to 1.2,
        "Lightly Active" to 1.375,
        "Moderately Active" to 1.55,
        "Very Active" to 1.725,
        "Extra Active" to 1.9
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPreferences =
            requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        if (!sharedPreferences.contains("age")){
            findNavController().navigate(R.id.action_caloriesCalcResult1_to_warning2)
        }
        val view = inflater.inflate(R.layout.fragment_calories_calc_result1, container, false)
        val sex = sharedPreferences.getString("gender", "")
        val weight = sharedPreferences.getFloat("weight", 0F)
        val height = sharedPreferences.getFloat("height", 0F)
        val age = sharedPreferences.getFloat("age", 0F)
        val activityLevel = sharedPreferences.getString("activityLevel", "")
        val dailyCalories = calculateCalories(sex, weight, height, age, activityLevel)

        val resultTextView = view.findViewById<TextView>(R.id.textViewResult)
        resultTextView.text = "Daily Calories: $dailyCalories"

        return view
    }
    private fun calculateCalories(sex: String?, weightLb: Float, heightIn: Float, age: Float, activityLevel: String?): Double{

        val bmr: Double

        if (sex == "male") {
            bmr = 88.362 + (13.397 * weightLb) + (4.799 * heightIn) - (5.677 * age)
        } else {
            bmr = 447.593 + (9.247 * weightLb) + (3.098 * heightIn) - (4.330 * age)
        }


        val activityLevelMultiplier = activityLevelMultiplierMap[activityLevel] ?: 1.0


        val calculatedCalories = bmr * activityLevelMultiplier

        val roundedCalories = BigDecimal(calculatedCalories).setScale(2, RoundingMode.HALF_EVEN).toDouble()


        return roundedCalories
    }
}