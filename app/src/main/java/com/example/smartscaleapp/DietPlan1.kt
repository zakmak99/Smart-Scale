package com.example.smartscaleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager


class DietPlan1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_diet_plan1, container, false)

        val submitButton = view.findViewById<Button>(R.id.submitButton)
        val mealsPerDayEditText = view.findViewById<EditText>(R.id.mealsPerDayEditText)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        submitButton.setOnClickListener {
            val age = view.findViewById<EditText>(R.id.ageEditText).text.toString()
            val goal = view.findViewById<EditText>(R.id.goalEditText).text.toString()
            val activityLevel = view.findViewById<EditText>(R.id.activityLevelEditText).text.toString()
            val mealsPerDay = mealsPerDayEditText.text.toString()

            val mealsPerDayValue = mealsPerDay.toIntOrNull() ?: 0
            val numberOfMealsToDisplay = when {
                mealsPerDayValue < 1 -> 1
                mealsPerDayValue > 3 -> 3
                else -> mealsPerDayValue
            }

            val foodItemList = generateFoodItemList(numberOfMealsToDisplay)

            val adapter = DietPlanGainAdapter(foodItemList)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            val intent = Intent(requireActivity(), when {
                goal.equals("gain", ignoreCase = true) -> DietPlanGain::class.java
                goal.equals("lose", ignoreCase = true) -> DietPlanLose::class.java
                else -> return@setOnClickListener
            })

            intent.apply {
                putExtra("age", age)
                putExtra("activityLevel", activityLevel)
                putExtra("mealsPerDay", mealsPerDay)
            }

            if (age.isBlank() || goal.isBlank() || activityLevel.isBlank() || mealsPerDay.isBlank()) {
                // Shows an error message to the user
                Toast.makeText(requireContext(), "Please fill in your info.", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(intent)
            }
        }

        return view
    }

    private fun generateFoodItemList(numberOfMeals: Int): List<FoodItem> {

        return when (numberOfMeals) {
            1 -> listOf(
                FoodItem("Meal 1", 500, 20.0),
            )
            2 -> listOf(
                FoodItem("Meal 1", 500, 20.0),
                FoodItem("Meal 2", 600, 25.0),
            )
            else -> listOf(
                FoodItem("Meal 1", 500, 20.0),
                FoodItem("Meal 2", 600, 25.0),
                FoodItem("Meal 3", 700, 30.0),
            )
        }

    }

    }


