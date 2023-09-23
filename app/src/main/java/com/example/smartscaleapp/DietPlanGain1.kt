package com.example.smartscaleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DietPlanGain1 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val age = it.getString("age")
            val activityLevel = it.getString("activityLevel")
            val mealsPerDay = it.getString("mealsPerDay")

            val foodItemList = mutableListOf<FoodItem>()

            if (mealsPerDay != null && age != null) {

                val ageInt = age.toIntOrNull()

                if (ageInt != null) {
                    when (ageInt) {
                        in 0 until 18 -> {
                            when {
                                mealsPerDay.toInt() == 1 -> {
                                    foodItemList.add(FoodItem("Kid's Meal", 300, 15.0))
                                }

                                mealsPerDay.toInt() == 2 -> {
                                    foodItemList.add(FoodItem("Kid's Meal", 300, 15.0))
                                    foodItemList.add(FoodItem("Kid's Meal", 400, 20.0))
                                }

                                mealsPerDay.toInt() >= 3 -> {
                                    foodItemList.add(FoodItem("Kid's Meal", 300, 15.0))
                                    foodItemList.add(FoodItem("Kid's Meal", 400, 20.0))
                                    foodItemList.add(FoodItem("Kid's Meal", 350, 18.0))
                                }
                            }
                        }

                        in 18..40 -> {
                            when {
                                mealsPerDay.toInt() == 1 -> {
                                    foodItemList.add(FoodItem("Adult Meal", 500, 25.0))
                                }

                                mealsPerDay.toInt() == 2 -> {
                                    foodItemList.add(FoodItem("Adult Meal", 500, 25.0))
                                    foodItemList.add(FoodItem("Adult Meal", 600, 30.0))
                                }

                                mealsPerDay.toInt() >= 3 -> {
                                    foodItemList.add(FoodItem("Adult Meal", 500, 25.0))
                                    foodItemList.add(FoodItem("Adult Meal", 600, 30.0))
                                    foodItemList.add(FoodItem("Adult Meal", 550, 27.5))
                                }
                            }
                        }

                        else -> {
                            when {
                                mealsPerDay.toInt() == 1 -> {
                                    foodItemList.add(FoodItem("Senior Meal", 400, 20.0))
                                }

                                mealsPerDay.toInt() == 2 -> {
                                    foodItemList.add(FoodItem("Senior Meal", 400, 20.0))
                                    foodItemList.add(FoodItem("Senior Meal", 450, 22.5))
                                }

                                mealsPerDay.toInt() >= 3 -> {
                                    foodItemList.add(FoodItem("Senior Meal", 400, 20.0))
                                    foodItemList.add(FoodItem("Senior Meal", 450, 22.5))
                                    foodItemList.add(FoodItem("Senior Meal", 400, 20.0))
                                }
                            }
                        }
                    }
                }
            }

            // Now you have the foodItemList ready for use
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diet_plan_gain1, container, false)
    }
}