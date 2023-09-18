package com.example.smartscaleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button


class BMIResults1 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bmi_results1, container, false)

        // Retrieve BMI and result text from arguments
        val bmi = arguments?.getFloat("bmi", 0f) ?: 0f
        val result = arguments?.getString("result") ?: ""

        // Update UI elements with BMI and result
        val bmiTextView = view.findViewById<TextView>(R.id.bmiTextView)
        val resultTextView = view.findViewById<TextView>(R.id.resultTextView)

        bmiTextView.text = "BMI: $bmi"
        resultTextView.text = "Result: $result"

        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }


        return view

    }



}