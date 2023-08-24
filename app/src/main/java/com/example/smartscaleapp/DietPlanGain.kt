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

        val foodItemList = listOf(
            FoodItem("Chicken and white Rice", 526, 58.4),
            FoodItem("Salmon", 175, 22.1),

        )

        val adapter = DietPlanGainAdapter(foodItemList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}