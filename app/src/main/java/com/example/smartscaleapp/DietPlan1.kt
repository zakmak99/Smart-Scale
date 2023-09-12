package com.example.smartscaleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class DietPlan1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_diet_plan1, container, false)

        val gainButton = view.findViewById<Button>(R.id.buttonGain)
        val loseButton = view.findViewById<Button>(R.id.buttonLose)

        gainButton.setOnClickListener {
            // Handle "gain" button click
            // Navigate to the next fragment or perform any action you want
        }

        loseButton.setOnClickListener {
            // Handle "lose" button click
            // Navigate to the next fragment or perform any action you want
        }

        return view
    }
    }

