package com.example.smartscaleapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Button
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode
import android.widget.RadioGroup
import androidx.navigation.fragment.findNavController

class CaloriesCalculator1 : Fragment() {

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
         val sharedPreferences =
            requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val weight = view.findViewById<EditText>(R.id.editTextNumberWeight)?.text.toString().toFloat()
        val height = view.findViewById<EditText>(R.id.editTextNumberHeight)?.text.toString().toFloat()
        val age = view.findViewById<EditText>(R.id.editTextNumberAge)?.text.toString().toFloat()

        val radioGroupGender = view.findViewById<RadioGroup>(R.id.radioGroupGender)
        val selectedGenderId = radioGroupGender.checkedRadioButtonId
        val sex = if (selectedGenderId == R.id.radioButtonMale) "male" else "female"

        val activityLevelSpinner = view.findViewById<Spinner>(R.id.spinnerActivityLevel)
        val selectedActivityLevel = activityLevelSpinner.selectedItem.toString()

        editor.putFloat("weight", weight)
        editor.putFloat("height", height)
        editor.putString("gender", sex)
        editor.putFloat("age", age)
        editor.putString("activityLevel", selectedActivityLevel)
        editor.apply()


        // Use findNavController from the Fragment to navigate
        findNavController().navigate(R.id.action_caloriesCalculator1_to_title2)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calories_calculator1, container, false)

        // Retrieve data from SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val weight = sharedPreferences.getFloat("weight", 0.0f)
        val height = sharedPreferences.getFloat("height", 0.0f)
        val age = sharedPreferences.getFloat("age", 0.0f)
        val gender = sharedPreferences.getString("gender", "male")
        val activityLevel = sharedPreferences.getString("activityLevel", "Sedentary")

        // Set the saved data in the respective fields
        val editTextWeight = view.findViewById<EditText>(R.id.editTextNumberWeight)
        val editTextHeight = view.findViewById<EditText>(R.id.editTextNumberHeight)
        val editTextAge = view.findViewById<EditText>(R.id.editTextNumberAge)
        val radioGroupGender = view.findViewById<RadioGroup>(R.id.radioGroupGender)
        val activityLevelSpinner = view.findViewById<Spinner>(R.id.spinnerActivityLevel)
        if(age != 0.0f) {
            editTextWeight.setText(weight.toString())
            editTextHeight.setText(height.toString())
            editTextAge.setText(age.toString())
        }
        if (gender == "male") {
            radioGroupGender.check(R.id.radioButtonMale)
        } else {
            radioGroupGender.check(R.id.radioButtonFemale)
        }

        // Set up the spinner
        val activityLevels = resources.getStringArray(R.array.activity_levels)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, activityLevels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activityLevelSpinner.adapter = adapter

        val position = activityLevels.indexOf(activityLevel)
        if (position != -1) {
            activityLevelSpinner.setSelection(position)
        }

        // Set up the edit hint text color
        val newHintColor = Color.parseColor("#0FE1FF")
        editTextWeight.setHintTextColor(newHintColor)
        editTextHeight.setHintTextColor(newHintColor)
        editTextAge.setHintTextColor(newHintColor)
        // Set the onClickListener for the calculate button
        val saveButton = view.findViewById<Button>(R.id.buttonSave)
        saveButton.setOnClickListener {
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
        val cancelButton = view.findViewById<Button>(R.id.buttonCancel)
        cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_caloriesCalculator1_to_title2)
        }


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