package com.example.smartscaleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bmiCalcButton = findViewById<Button>(R.id.buttonBMICalc)
        bmiCalcButton.setOnClickListener{
            val intent = Intent(this, BMICalculator::class.java)
            startActivity(intent)
        }


        val caloriesCalButton = findViewById<Button>(R.id.buttonCaloriesCalc)
        caloriesCalButton.setOnClickListener{
            val intent = Intent(this, CaloriesCalculator::class.java)
            startActivity(intent)
        }


        val dietPlanButton = findViewById<Button>(R.id.buttonDietPlan)
        dietPlanButton.setOnClickListener{
            val Intent = Intent(this, DietPlan::class.java)
            startActivity(Intent)
        }

        val myDrawer = findViewById<DrawerLayout>(R.id.my_drawer_layout)
        findViewById<ImageButton>(R.id.navMenuButton)
            .setOnClickListener {
                myDrawer.openDrawer(GravityCompat.START)
                while (myDrawer.isDrawerVisible(GravityCompat.START)){
                myDrawer.bringToFront()
                }
            }
        onMenuItemSelected()
    }

}