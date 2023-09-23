package com.example.smartscaleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EveryMeal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_every_meal)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val foodItemList = listOf(
            FoodItem("Seared Salmon with Charred Green Beans", 350, 25.0),
            FoodItem("Air Fryer Squash Soup", 280, 10.5),
            FoodItem("Sheet Pan Fish and Vegetables", 320, 20.0),
            FoodItem("Icy Shrimp Lettuce Wraps", 280, 15.0),
            FoodItem("White Bean and Kale Toasts", 220, 10.0),
            FoodItem("Garam Masala Roasted Chicken and Cauliflower", 420, 30.0),
            FoodItem("Roasted Cauliflower Tacos", 380, 15.5),
            FoodItem("Balsamic Glazed Pork with Roasted Butternut Squash", 420, 25.0),
            FoodItem("Shrimp Ceviche", 230, 15.5),
            FoodItem("Creamy Cauliflower Soup with Almond-Thyme Gremolata", 290, 10.5),
            FoodItem("Fiery Pork Lettuce Wraps", 350, 20.0),
            FoodItem("Air Fryer Stuffed Chicken", 310, 25.0),
            FoodItem("Tandoori Chicken", 320, 26.0),
            FoodItem("California Roll Salad", 350, 15.0),
            FoodItem("Shakshuka", 220, 10.5),
            FoodItem("Paprika Chicken with Crispy Chickpeas and Tomatoes", 420, 25.5),
            FoodItem("Pork Chops with Bloody Mary Tomato Salad", 320, 20.0),
            FoodItem("Grilled Chicken Skewers and Kale Caesar", 350, 30.0),
            FoodItem("Moroccan Meatballs", 340, 18.5),
            FoodItem("Spring Panzanella", 220, 8.5),
            FoodItem("Roasted Salmon with Green Beans and Tomatoes", 320, 20.5),
            FoodItem("Yorkshire pudding wrap", 480, 35.0),
            FoodItem("Spiced Fresh Tomato Soup with Sweet and Herby Pitas", 280, 8.5),
            FoodItem("Vegetable Ramen with Mushrooms and Bok Choy", 310, 10.0),
            FoodItem("Grilled Steak Tortilla Salad", 400, 28.0),
            FoodItem("Roasted Cauliflower Pizza", 350, 12.5),
            FoodItem("Striped Bass with Radish Salsa Verde", 290, 25.0),
            FoodItem("Korean Pineapple Beef Lettuce Wraps", 330, 22.5),
            FoodItem("Vegan Black Bean Soup", 280, 14.0),
            FoodItem("Honey-Ginger Cedar Plank Salmon", 360, 24.5),
            FoodItem("Traditional Chicken Curry", 340, 20.0)
            )



        val adapter = DietPlanGainAdapter(foodItemList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }


}