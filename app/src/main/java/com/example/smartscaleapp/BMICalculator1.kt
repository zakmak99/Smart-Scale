package com.example.smartscaleapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.content.SharedPreferences

class BMICalculator1 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bmi_calculator1, container, false)

        // Initialize SharedPreferences
        val sharedPreferences =
            requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val calculateButton = view.findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            calculateBMI(view, editor)
        }

        return view
    }

    private fun calculateBMI(view: View, editor: SharedPreferences.Editor) {
        val weightEditText = view.findViewById<EditText>(R.id.weightEditText)
        val heightEditText = view.findViewById<EditText>(R.id.heightEditText)
        val genderRadioGroup = view.findViewById<RadioGroup>(R.id.genderRadioGroup)

        val weightInput = weightEditText.text.toString()
        val heightInput = heightEditText.text.toString()

        if (weightInput.isBlank()) {
            // Show an error message to the user for weight input
            weightEditText.error = "Please enter weight"
            return
        }

        if (heightInput.isBlank()) {
            // Show an error message to the user for height input
            heightEditText.error = "Please enter height"
            return
        }

        val weight = weightInput.toFloat()
        val height = heightInput.toFloat() / 100 // Convert cm to m
        val gender =
            if (genderRadioGroup.checkedRadioButtonId == R.id.maleRadioButton) "male" else "female"

        // Calculate BMI
        val bmi = weight / (height * height)

        // Save weight, height, and gender in SharedPreferences
        editor.putFloat("weight", weight)
        editor.putFloat("height", height)
        editor.putString("gender", gender)
        editor.putFloat("bmi", bmi)
        editor.apply()


        val bmiResultsFragment = BMIResults1()


        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, bmiResultsFragment)
        transaction.addToBackStack(null)  // Optional: This allows the user to go back to the calculator
        transaction.commit()

        val calculateButton = view.findViewById<Button>(R.id.calculateButton)
        calculateButton.visibility = View.GONE


    }
}
