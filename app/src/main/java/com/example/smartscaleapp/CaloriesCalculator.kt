package com.example.smartscaleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.AdapterView



class CaloriesCalculator : AppCompatActivity() {

    private val activityLevelMultiplierMap = mapOf(
        "Sedentary" to 1.2,
        "Lightly Active" to 1.375,
        "Moderately Active" to 1.55,
        "Very Active" to 1.725,
        "Extra Active" to 1.9
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories_calculator)



        val calculateButton = findViewById<Button>(R.id.buttonCalculate)
        calculateButton.setOnClickListener {
            if(areFieldsValid()){
                calculateCalories()
            }
            else{
                Toast.makeText(this, "Please complete all the fields", Toast.LENGTH_SHORT).show()
            }
        }

        val activityLevelSpinner = findViewById<Spinner>(R.id.spinnerActivityLevel)
        val activityLevels = resources.getStringArray(R.array.activity_levels)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, activityLevels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activityLevelSpinner.adapter = adapter



    }



    private fun areFieldsValid():Boolean{
        val weightText = findViewById<EditText>(R.id.editTextNumberWeight).text.toString()
        val heightText = findViewById<EditText>(R.id.editTextNumberHeight).text.toString()
        val ageText = findViewById<EditText>(R.id.editTextNumberAge).text.toString()

        return weightText.isNotBlank() && heightText.isNotBlank() && ageText.isNotBlank()
    }



    private fun calculateCalories(){

        val weight = findViewById<EditText>(R.id.editTextNumberWeight).text.toString().toDoubleOrNull()?:0.0
        val height = findViewById<EditText>(R.id.editTextNumberHeight).text.toString().toDoubleOrNull()?:0.0
        val age = findViewById<EditText>(R.id.editTextNumberAge).text.toString().toIntOrNull()?:0

        val radioGroupGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        val selectedGenderId = radioGroupGender.checkedRadioButtonId
        val sex = if (selectedGenderId == R.id.radioButtonMale) "male" else "female"


        val dailyCalories = calculateCalories( sex, weight, height, age)

        val activityLevelSpinner = findViewById<Spinner>(R.id.spinnerActivityLevel)
        val selectedActivityLevel = activityLevelSpinner.selectedItem.toString().trim()


        val intent = Intent(this, CaloriesCalcResult::class.java)
        intent.putExtra("dailyCalories", dailyCalories)
        intent.putExtra("activityLevel", selectedActivityLevel)
        startActivity(intent)

    }



       private fun calculateCalories(sex: String, weightLb: Double, heightIn: Double, age: Int): Double{

       val bmr: Double

        if (sex == "male") {
            bmr = 88.362 + (13.397 * weightLb) + (4.799 * heightIn) - (5.677 * age)
        } else {
            bmr = 447.593 + (9.247 * weightLb) + (3.098 * heightIn) - (4.330 * age)
        }

//           val activityLevelMultiplierMap = mapOf(
//               "Sedentary" to 1.2,
//               "Lightly Active" to 1.375,
//               "Moderately Active" to 1.55,
//               "Very Active" to 1.725,
//               "Extra Active" to 1.9
//           )

           val activityLevelSpinner = findViewById<Spinner>(R.id.spinnerActivityLevel)
           val selectedActivityLevel = activityLevelSpinner.selectedItem.toString()

           val activityLevelMultiplier = activityLevelMultiplierMap[selectedActivityLevel] ?: 1.0


           val calculatedCalories = bmr * activityLevelMultiplier

           val roundedCalories = BigDecimal(calculatedCalories).setScale(2, RoundingMode.HALF_EVEN).toDouble()


           return roundedCalories
    }



}

