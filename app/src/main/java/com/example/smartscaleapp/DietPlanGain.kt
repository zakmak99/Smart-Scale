package com.example.smartscaleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DietPlanGain : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_gain)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val age = intent.getStringExtra("age")
        val activityLevel = intent.getStringExtra("activityLevel")
        val mealsPerDay = intent.getStringExtra("mealsPerDay")

        val foodItemList = mutableListOf<FoodItem>()

        if (mealsPerDay != null && age != null) {

            val ageInt = age.toIntOrNull()

            if (ageInt != null) {
                when (ageInt) {
                    in 0 until 18 -> {
                        when {
                            mealsPerDay.toInt() == 1 -> {
                                // Add meals for ages under 18 with 1 meal per day
                                foodItemList.add(FoodItem("Kid's Meal", 300, 15.0))
                            }

                            mealsPerDay.toInt() == 2 -> {
                                // Add meals for ages under 18 with 2 meal per day
                                foodItemList.add(FoodItem("Kid's Meal", 300, 15.0))
                                foodItemList.add(FoodItem("Kid's Meal", 400, 20.0))
                            }

                            mealsPerDay.toInt() >= 3 -> {
                                // Add meals for ages under 18 with 3 meal per day
                                foodItemList.add(FoodItem("Kid's Meal", 300, 15.0))
                                foodItemList.add(FoodItem("Kid's Meal", 400, 20.0))
                                foodItemList.add(FoodItem("Kid's Meal", 350, 18.0))
                            }

                            else -> {
                                // Handle other cases
                            }
                        }
                    }

                    in 18..40 -> {
                        when {
                            mealsPerDay.toInt() == 1 -> {
                                // Add meals for ages 18-40
                                foodItemList.add(FoodItem("Adult Meal", 500, 25.0))
                            }

                            mealsPerDay.toInt() == 2 -> {
                                // Add meals for ages 18-40
                                foodItemList.add(FoodItem("Adult Meal", 500, 25.0))
                                foodItemList.add(FoodItem("Adult Meal", 600, 30.0))
                            }

                            mealsPerDay.toInt() >= 3 -> {
                                // Add meals for ages 18-40
                                foodItemList.add(FoodItem("Adult Meal", 500, 25.0))
                                foodItemList.add(FoodItem("Adult Meal", 600, 30.0))
                                foodItemList.add(FoodItem("Adult Meal", 550, 27.5))
                            }

                            else -> {
                                // Handle other cases
                            }
                        }
                    }

                    else -> {
                        when {
                            mealsPerDay.toInt() == 1 -> {
                                /// Add meals for ages over 40
                                foodItemList.add(FoodItem("Senior Meal", 400, 20.0))
                            }

                            mealsPerDay.toInt() == 2 -> {
                                // Add meals for ages over 40
                                foodItemList.add(FoodItem("Senior Meal", 400, 20.0))
                                foodItemList.add(FoodItem("Senior Meal", 450, 22.5))
                            }

                            mealsPerDay.toInt() >= 3 -> {
                                // Add meals for ages over 40
                                foodItemList.add(FoodItem("Senior Meal", 400, 20.0))
                                foodItemList.add(FoodItem("Senior Meal", 450, 22.5))
                                foodItemList.add(FoodItem("Senior Meal", 400, 20.0))
                            }
                        }
                    }
                }
            }
        }

        val adapter = DietPlanGainAdapter(foodItemList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}