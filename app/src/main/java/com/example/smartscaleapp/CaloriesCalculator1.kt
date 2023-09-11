package com.example.smartscaleapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.RadioButton
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode
import android.widget.RadioGroup
import android.widget.AdapterView
import android.util.Size
import androidx.navigation.fragment.findNavController

class CaloriesCalculator1 : Fragment() {
    private val activityLevelMultiplierMap = mapOf(
        "Sedentary" to 1.2,
        "Lightly Active" to 1.375,
        "Moderately Active" to 1.55,
        "Very Active" to 1.725,
        "Extra Active" to 1.9
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun areFieldsValid(view: View): Boolean {
        val weightText = view.findViewById<EditText>(R.id.editTextNumberWeight)?.text.toString()
        val heightText = view.findViewById<EditText>(R.id.editTextNumberHeight)?.text.toString()
        val ageText = view.findViewById<EditText>(R.id.editTextNumberAge)?.text.toString()

        return weightText.isNotBlank() && heightText.isNotBlank() && ageText.isNotBlank()
    }
    private fun calculateCalories(view: View) {
        val weight = view.findViewById<EditText>(R.id.editTextNumberWeight)?.text.toString().toDoubleOrNull() ?: 0.0
        val height = view.findViewById<EditText>(R.id.editTextNumberHeight)?.text.toString().toDoubleOrNull() ?: 0.0
        val age = view.findViewById<EditText>(R.id.editTextNumberAge)?.text.toString().toIntOrNull() ?: 0

        val radioGroupGender = view.findViewById<RadioGroup>(R.id.radioGroupGender)
        val selectedGenderId = radioGroupGender.checkedRadioButtonId
        val sex = if (selectedGenderId == R.id.radioButtonMale) "male" else "female"

        val dailyCalories = calculateCalories(sex, weight, height, age)

        val activityLevelSpinner = view.findViewById<Spinner>(R.id.spinnerActivityLevel)
        val selectedActivityLevel = activityLevelSpinner.selectedItem.toString().trim()

        // Bundle to pass data to the next fragment
        val bundle = Bundle()
        bundle.putDouble("calories", dailyCalories)
        bundle.putString("activityLevel", selectedActivityLevel)


        // Use findNavController from the Fragment to navigate
        findNavController().navigate(R.id.action_caloriesCalculator1_to_caloriesCalcResult1)
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

        val activityLevelSpinner = view?.findViewById<Spinner>(R.id.spinnerActivityLevel)
        val selectedActivityLevel = activityLevelSpinner?.selectedItem.toString()

        val activityLevelMultiplier = activityLevelMultiplierMap[selectedActivityLevel] ?: 1.0


        val calculatedCalories = bmr * activityLevelMultiplier

        val roundedCalories = BigDecimal(calculatedCalories).setScale(2, RoundingMode.HALF_EVEN).toDouble()


        return roundedCalories
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calories_calculator1, container, false)

        // Set the onClickListener for the calculate button
        val calculateButton = view.findViewById<Button>(R.id.buttonCalculate)
        calculateButton.setOnClickListener {
            if (areFieldsValid(view)) {
                calculateCalories(view)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please complete all the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Set up the spinner
        val activityLevelSpinner = view.findViewById<Spinner>(R.id.spinnerActivityLevel)
        val activityLevels = resources.getStringArray(R.array.activity_levels)
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, activityLevels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activityLevelSpinner.adapter = adapter

        // Set up the edit hint text color
        val editTextWeight = view.findViewById<EditText>(R.id.editTextNumberWeight)
        val editTextHeight = view.findViewById<EditText>(R.id.editTextNumberHeight)
        val editTextAge = view.findViewById<EditText>(R.id.editTextNumberAge)
        val newHintColor = Color.parseColor("#0FE1FF")
        editTextWeight.setHintTextColor(newHintColor)
        editTextHeight.setHintTextColor(newHintColor)
        editTextAge.setHintTextColor(newHintColor)

        return view


}
}


/*override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    val calculateButton = view.findViewById<Button>(R.id.buttonCalculate)
    calculateButton.setOnClickListener {
        if(areFieldsValid()){
            calculateCalories()
        }
        else{
            Toast.makeText(it, "Please complete all the fields", Toast.LENGTH_SHORT).show()
        }
    }
    val view = inflater.inflate(R.layout.fragment_calories_calculator1, container, false)
    //Spinner Code
    val activityLevelSpinner = view.findViewById<Spinner>(R.id.spinnerActivityLevel)
    val activityLevels = resources.getStringArray(R.array.activity_levels)
    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, activityLevels)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    activityLevelSpinner.adapter = adapter


    //Edit hint text Color
    val editTextWeight = view.findViewById<EditText>(R.id.editTextNumberWeight)
    val editTextHeight = view.findViewById<EditText>(R.id.editTextNumberHeight)
    val editTextAge = view.findViewById<EditText>(R.id.editTextNumberAge)
    val newHintColor = Color.parseColor("#0FE1FF")

    editTextWeight.setHintTextColor(newHintColor)
    editTextHeight.setHintTextColor(newHintColor)
    editTextAge.setHintTextColor(newHintColor)
    return view
}




private fun areFieldsValid():Boolean{
    val weightText = view.findViewById<EditText>(R.id.editTextNumberWeight).text.toString()
    val heightText = view.findViewById<EditText>(R.id.editTextNumberHeight).text.toString()
    val ageText = view.findViewById<EditText>(R.id.editTextNumberAge).text.toString()

    return weightText.isNotBlank() && heightText.isNotBlank() && ageText.isNotBlank()
}



private fun calculateCalories(){

    val weight = view.findViewById<EditText>(R.id.editTextNumberWeight).text.toString().toDoubleOrNull()?:0.0
    val height = view.findViewById<EditText>(R.id.editTextNumberHeight).text.toString().toDoubleOrNull()?:0.0
    val age = view.findViewById<EditText>(R.id.editTextNumberAge).text.toString().toIntOrNull()?:0

    val radioGroupGender = view.findViewById<RadioGroup>(R.id.radioGroupGender)
    val selectedGenderId = radioGroupGender.checkedRadioButtonId
    val sex = if (selectedGenderId == R.id.radioButtonMale) "male" else "female"


    val dailyCalories = calculateCalories( sex, weight, height, age)

    val activityLevelSpinner = view.findViewById<Spinner>(R.id.spinnerActivityLevel)
    val selectedActivityLevel = activityLevelSpinner.selectedItem.toString().trim()


    findNavController().navigate(R.id.action_caloriesCalculator1_to_caloriesCalcResult1)

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

    val activityLevelSpinner = view.findViewById<Spinner>(R.id.spinnerActivityLevel)
    val selectedActivityLevel = activityLevelSpinner.selectedItem.toString()

    val activityLevelMultiplier = activityLevelMultiplierMap[selectedActivityLevel] ?: 1.0


    val calculatedCalories = bmr * activityLevelMultiplier

    val roundedCalories = BigDecimal(calculatedCalories).setScale(2, RoundingMode.HALF_EVEN).toDouble()


    return roundedCalories
}*/