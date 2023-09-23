package com.example.smartscaleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DietPlanLose : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_lose)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val age = intent.getStringExtra("age")
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
                                foodItemList.add(FoodItem("Seared Salmon with Charred Green Beans", 350, 25.0))
                            }

                            mealsPerDay.toInt() == 2 -> {
                                // Add meals for ages under 18 with 2 meals per day
                                foodItemList.add(FoodItem("Air Fryer Squash Soup", 300, 15.0))
                                foodItemList.add(FoodItem("Sheet Pan Fish and Vegetables", 400, 20.0))
                            }

                            mealsPerDay.toInt() >= 3 -> {
                                // Add meals for ages under 18 with 3 or more meals per day
                                foodItemList.add(FoodItem("Icy Shrimp Lettuce Wraps", 350, 18.0))
                                foodItemList.add(FoodItem("White Bean and Kale Toasts", 325, 17.0))
                                foodItemList.add(FoodItem("Garam Masala Roasted Chicken and Cauliflower", 350, 18.0))
                            }
                        }
                    }

                    in 18..40 -> {
                        // Add meals for ages 18-40
                        when {
                            mealsPerDay.toInt() == 1 -> {
                                foodItemList.add(FoodItem("Spiced Fresh Tomato Soup with Sweet and Herby Pitas", 350, 20.0))
                            }

                            mealsPerDay.toInt() == 2 -> {
                                foodItemList.add(FoodItem("Grilled Steak Tortilla Salad", 325, 18.0))
                                foodItemList.add(FoodItem("Roasted Cauliflower Pizza", 375, 20.0))
                            }

                            mealsPerDay.toInt() >= 3 -> {
                                foodItemList.add(FoodItem("Striped Bass with Radish Salsa Verde", 350, 18.0))
                                foodItemList.add(FoodItem("Korean Pineapple Beef Lettuce Wraps", 330, 17.0))
                                foodItemList.add(FoodItem("Vegan Black Bean Soup", 350, 18.0))
                            }
                        }
                    }

                    else -> {
                        // Add meals for ages over 40
                        when {
                            mealsPerDay.toInt() == 1 -> {
                                foodItemList.add(FoodItem("Honey-Ginger Cedar Plank Salmon", 325, 17.0))
                            }

                            mealsPerDay.toInt() == 2 -> {
                                foodItemList.add(FoodItem("Traditional Chicken Curry", 350, 18.0))
                                foodItemList.add(FoodItem("Moroccan Meatballs", 330, 17.0))
                            }

                            mealsPerDay.toInt() >= 3 -> {
                                foodItemList.add(FoodItem("Spring Panzanella", 350, 18.0))
                                foodItemList.add(FoodItem("Roasted Salmon with Green Beans and Tomatoes", 350, 18.0))
                                foodItemList.add(FoodItem("Yorkshire pudding wrap", 350, 18.0))
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
