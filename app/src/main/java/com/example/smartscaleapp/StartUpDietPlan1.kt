package com.example.smartscaleapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class StartUpDietPlan1 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_start_up_diet_plan1, container, false)

        val dietPlanButton = view.findViewById<Button>(R.id.buttonDietPlan)
        dietPlanButton.setOnClickListener {
            val intent = Intent(requireActivity(), DietPlan::class.java)
            startActivity(intent)
        }

        return view

    }


}