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
                                foodItemList.add(FoodItem("Seared Salmon with Charred Green Beans", 488, 31.0))
                            }

                            mealsPerDay.toInt() == 2 -> {
                                // Add meals for ages under 18 with 2 meal per day
                                foodItemList.add(FoodItem("Seared Salmon with Charred Green Beans", 488, 31.0))
                                foodItemList.add(FoodItem("Air Fryer Squash Soup", 487, 24.1))
                            }

                            mealsPerDay.toInt() >= 3 -> {
                                // Add meals for ages under 18 with 3 meal per day
                                foodItemList.add(FoodItem("Seared Salmon with Charred Green Beans", 488, 31.0))
                                foodItemList.add(FoodItem("Air Fryer Squash Soup", 487, 24.1))
                                foodItemList.add(FoodItem("Sheet Pan Fish and Vegetables", 487, 24.1))
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
                                foodItemList.add(FoodItem("Garam Masala Roasted Chicken and Cauliflower", 420, 30.0))
                            }

                            mealsPerDay.toInt() == 2 -> {
                                // Add meals for ages 18-40
                                foodItemList.add(FoodItem("Grilled Steak Tortilla Salad", 480, 28.0))
                                foodItemList.add(FoodItem("Roasted Cauliflower Pizza", 420, 24.0))
                            }

                            mealsPerDay.toInt() >= 3 -> {
                                // Add meals for ages 18-40
                                foodItemList.add(FoodItem("Garam Masala Roasted Chicken and Cauliflower", 420, 30.0))
                                foodItemList.add(FoodItem("Grilled Steak Tortilla Salad", 480, 28.0))
                                foodItemList.add(FoodItem("Roasted Cauliflower Pizza", 420, 24.0))
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
                                foodItemList.add(FoodItem("Korean Pineapple Beef Lettuce Wraps", 488, 31.0))
                            }

                            mealsPerDay.toInt() == 2 -> {
                                // Add meals for ages over 40
                                foodItemList.add(FoodItem("Korean Pineapple Beef Lettuce Wraps", 488, 31.0))
                                foodItemList.add(FoodItem("Traditional Chicken Curry", 487, 24.1))
                        }

                            mealsPerDay.toInt() >= 3 -> {
                                // Add meals for ages over 40
                                foodItemList.add(FoodItem("Korean Pineapple Beef Lettuce Wraps", 488, 31.0))
                                foodItemList.add(FoodItem("Traditional Chicken Curry", 487, 24.1))
                                foodItemList.add(FoodItem("Paprika Chicken with Crispy Chickpeas and Tomatoes", 488, 31.0))
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