package com.example.smartscaleapp

data class FoodItem(val name: String, val calories: Int, val protein: Double, var isFavorite: Boolean = false)