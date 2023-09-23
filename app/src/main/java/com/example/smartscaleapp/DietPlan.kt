package com.example.smartscaleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.google.gson.Gson

class DietPlan : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_plan)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val age = findViewById<EditText>(R.id.ageEditText).text.toString()
            val goal = findViewById<EditText>(R.id.goalEditText).text.toString()
            //val activityLevel = findViewById<EditText>(R.id.activityLevelEditText).text.toString()
            val mealsPerDay = findViewById<EditText>(R.id.mealsPerDayEditText).text.toString()

            val mealsPerDayEditText = findViewById<EditText>(R.id.mealsPerDayEditText)
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

            submitButton.setOnClickListener {
                val mealsPerDay = mealsPerDayEditText.text.toString().toIntOrNull() ?: 0

                val numberOfMealsToDisplay = when {
                    mealsPerDay < 1 -> 1
                    mealsPerDay > 3 -> 3
                    else -> mealsPerDay
                }

                val foodItemList = generateFoodItemList(numberOfMealsToDisplay)

                val adapter = DietPlanGainAdapter(foodItemList)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this)
            }


            intent?.apply {
                putExtra("age", age)
                //putExtra("activityLevel", activityLevel)
                putExtra("mealsPerDay", mealsPerDay)
            }

            if (age.isBlank() || goal.isBlank() || mealsPerDay.isBlank()) {
                // Shows an error message to the user
                Toast.makeText(this, "Please fill in your info.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (age.isNotBlank() && goal.isNotBlank() && mealsPerDay.isNotBlank()) {
                val intent = when {
                    goal.equals("gain", ignoreCase = true) -> Intent(this, DietPlanGain::class.java)
                    goal.equals("lose", ignoreCase = true) -> Intent(this, DietPlanLose::class.java)
                    else -> null
                }

                intent?.apply {
                    putExtra("age", age)
                    //putExtra("activityLevel", activityLevel)
                    putExtra("mealsPerDay", mealsPerDay)
                }

                intent?.let {
                    startActivity(intent)
                }
            }
        }

        val activityLevelSpinner = findViewById<Spinner>(R.id.activityLevelSpinner)
        val activityLevels = resources.getStringArray(R.array.activity_levels)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, activityLevels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activityLevelSpinner.adapter = adapter


        var selectedActivityLevel: String // Variable to store the selected activity level

        activityLevelSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            )
            {
                selectedActivityLevel = activityLevels[position] // Store the selected activity level
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case where nothing is selected (if needed)
            }
        }
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

//    private fun saveFavoritesToSharedPreferences(favorites: List<FoodItem>) {
//        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        val gson = Gson() // You may need to add Gson to your dependencies
//
//        // Convert the list of favorites to a JSON string
//        val jsonFavorites = gson.toJson(favorites)
//
//        // Store the JSON string in SharedPreferences
//        editor.putString("favorites", jsonFavorites)
//        editor.apply()
//    }
}