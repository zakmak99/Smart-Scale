package com.example.smartscaleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.content.Intent
import android.widget.TextView
class BMICalculator1 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bmi_calculator1, container, false)


        val calculateButton = view.findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            calculateBMI(view)
        }

        return view
    }



    private fun calculateBMI(view: View) {
        val weightEditText = view.findViewById<EditText>(R.id.weightEditText)
        val heightEditText = view.findViewById<EditText>(R.id.heightEditText)
        val genderRadioGroup = view.findViewById<RadioGroup>(R.id.genderRadioGroup)

        val weightInput = weightEditText.text.toString()
        val heightInput = heightEditText.text.toString()

        if (weightInput.isBlank()) {
            // Show an error message to the user weight input
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
        val gender = if (genderRadioGroup.checkedRadioButtonId == R.id.maleRadioButton) "male" else "female"

        val bmi = weight / (height * height)

        val resultText = when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Normal Weight"
            bmi < 29.9 -> "Overweight"
            else -> "Obese"
        }

//        val intent = Intent(requireActivity(), BMIResults1::class.java)
//        intent.putExtra("bmi", bmi)
//        intent.putExtra("result", resultText)
//        requireActivity().startActivity(intent)



        // Pass the BMI and result text as arguments to the next fragment
        val bundle = Bundle()
        bundle.putFloat("bmi", bmi)
        bundle.putString("result", resultText)

        val bmiResultsFragment = BMIResults1()
        bmiResultsFragment.arguments = bundle

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, bmiResultsFragment)
        transaction.addToBackStack(null)  // Optional: This allows the user to go back to the calculator
        transaction.commit()

        val calculateButton = view.findViewById<Button>(R.id.calculateButton)
        calculateButton.visibility = View.GONE



    }



}