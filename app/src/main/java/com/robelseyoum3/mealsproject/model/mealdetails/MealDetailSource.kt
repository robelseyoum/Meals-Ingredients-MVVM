package com.robelseyoum3.mealsproject.model.mealdetails

import com.google.gson.annotations.SerializedName


data class MealDetailSource (
	@SerializedName("meals") val meals : List<Meals>
)



