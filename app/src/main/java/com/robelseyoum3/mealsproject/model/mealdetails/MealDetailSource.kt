package com.robelseyoum3.mealsproject.model.mealdetails

import com.google.gson.annotations.SerializedName
import com.robelseyoum3.mealsproject.model.mealdetails.Meals


data class MealDetailSource (

	@SerializedName("meals") val meals : List<Meals>
)