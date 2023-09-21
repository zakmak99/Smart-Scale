package com.example.smartscaleapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController


class BMIResults1 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val sharedPreferences =
                requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            val view = inflater.inflate(R.layout.fragment_bmi_results1, container, false)
            if (!sharedPreferences.contains("age")) {
                findNavController().navigate(R.id.action_BMIResults1_to_warning)
            }
            // Retrieve BMI from SharedPreferences
            val weight = sharedPreferences.getFloat("weight", 0F)
            val height = sharedPreferences.getFloat("height", 0F)
            val bmi = 703 * (weight / (height * height))

            // Retrieve result text from arguments (if needed)
            val result: String = getResult(bmi)

            // Update UI elements with BMI and result

            val bmiTextView = view.findViewById<TextView>(R.id.bmiTextView)
            val resultTextView = view.findViewById<TextView>(R.id.resultTextView)

            bmiTextView.text = "BMI: $bmi"
            resultTextView.text = "Result: $result"
            return view
        }

    override fun onResume() {
        super.onResume()
        val weight = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            .getFloat("weight", 0F)
        val height = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            .getFloat("height", 0F)
        val bmi = 703 * (weight / (height * height))
        val progressBar = view?.findViewById<ProgressBar>(R.id.bmiProgressBar)
        if (bmi <= 40) {
            if (progressBar != null) {
                progressBar.progress = bmi.toInt()
            }
        }else{
            if (progressBar != null) {
                progressBar.progress = 40
            }
        }


    }

    }
        fun getResult(bmi: Float): String {
            val result : String
            if(bmi < 18.5){
                result = "Underweight"
                return result
            }
            else if(bmi <24.9){
                result = "Normal"
                return result
            }
            else if(bmi < 29.9){
                result = "Overweight"
                return result
            }
            else if(bmi <34.9){
                result = "Obese Class 1"
                return result
            }
            else if(bmi <39.9){
                result = "Obese Class 2"
                return result
            }
            else{
                result = "Obese Class 3"
                return result
            }
        }