package com.example.smartscaleapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class Title : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_title, container, false)
        val sharedPreferences =
            requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        if( !sharedPreferences.contains("age")){
            findNavController().navigate(R.id.action_title2_to_caloriesCalculator1)
        }
        val profileButton = view.findViewById<ImageButton>(R.id.profileButton)
        profileButton.setOnClickListener {
            findNavController().navigate(R.id.action_title2_to_caloriesCalculator1)
        }
        return view

    }
}

