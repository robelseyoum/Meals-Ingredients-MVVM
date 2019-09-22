package com.robelseyoum3.mealsproject.model.specificcategries

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "specificMeal_table")
data class Meals (


	@SerializedName("strMeal") val strMeal : String,
	@SerializedName("strMealThumb") val strMealThumb : String,
	@PrimaryKey
	@SerializedName("idMeal") val idMeal : Int
)