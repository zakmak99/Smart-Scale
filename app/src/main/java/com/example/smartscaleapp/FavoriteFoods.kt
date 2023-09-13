package com.example.smartscaleapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
//import com.google.gson.Gson

class FavoriteFoods: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_lose)
    }
//    val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
//    val gson = Gson() // You may need to add Gson to your dependencies
//
//    // Retrieve the JSON string of favorites from SharedPreferences
//    val jsonFavorites = sharedPreferences.getString("favorites", null)
//
//    // Convert the JSON string back to a list of FoodItems
//    val favoriteFoodItems = gson.fromJson<List<FoodItem>>(jsonFavorites, object : TypeToken<List<FoodItem>>() {}.type)

}