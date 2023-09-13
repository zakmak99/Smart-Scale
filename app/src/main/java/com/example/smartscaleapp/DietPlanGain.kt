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

        if (mealsPerDay != null) {
            when {
                mealsPerDay.toInt() == 1 -> {
                    foodItemList.add(FoodItem("Philly Cheesesteak", 900, 50.0))
                }
                mealsPerDay.toInt() == 2 -> {
                    foodItemList.add(FoodItem("Meal 1", 500, 30.0))
                    foodItemList.add(FoodItem("Meal 2", 600, 40.0))
                }
                mealsPerDay.toInt() >= 3 -> {
                    foodItemList.add(FoodItem("Meal 1", 500, 30.0))
                    foodItemList.add(FoodItem("Meal 2", 600, 40.0))
                    foodItemList.add(FoodItem("Meal 3", 450, 25.0))
                }
                else -> {

                }
            }
        }

        val adapter = DietPlanGainAdapter(foodItemList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}