package com.example.smartscaleapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class Title : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_title, container, false)
        val signOutButton = view.findViewById<Button>(R.id.signOutButton)

        signOutButton.setOnClickListener {
            val intent = Intent(activity, SignOutActivity::class.java)
            startActivity(intent)
        }

        return view

        return inflater.inflate(R.layout.fragment_title, container, false)
    }
}

