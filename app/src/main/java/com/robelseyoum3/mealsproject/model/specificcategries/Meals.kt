package com.robelseyoum3.mealsproject.model.specificcategries

import com.google.gson.annotations.SerializedName

data class Meals (

	@SerializedName("strMeal") val strMeal : String,
	@SerializedName("strMealThumb") val strMealThumb : String,
	@SerializedName("idMeal") val idMeal : Int
)