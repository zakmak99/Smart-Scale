package com.example.smartscaleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DietPlanGainAdapter(private val itemList: List<FoodItem>) : RecyclerView.Adapter<DietPlanGainAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodNameTextView: TextView = itemView.findViewById(R.id.gainList)
        val caloriesTextView: TextView = itemView.findViewById(R.id.gainList)
        val proteinTextView: TextView = itemView.findViewById(R.id.gainList)
        val favoriteCheckBox: CheckBox = itemView.findViewById(R.id.favoriteCheck)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_diet_gain, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodItem = itemList[position]

        holder.foodNameTextView.text = foodItem.name
        holder.caloriesTextView.text = "Calories: ${foodItem.calories}"
        holder.proteinTextView.text = "Protein: ${foodItem.protein} g"
        holder.favoriteCheckBox.isChecked = foodItem.isFavorite
        holder.favoriteCheckBox.setOnCheckedChangeListener { _, isChecked ->
            foodItem.isFavorite = isChecked
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}