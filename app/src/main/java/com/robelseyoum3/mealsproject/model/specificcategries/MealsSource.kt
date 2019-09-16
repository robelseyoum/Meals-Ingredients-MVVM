package com.robelseyoum3.mealsproject.model.specificcategries

import com.google.gson.annotations.SerializedName


data class MealsSource (

	@SerializedName("meals") val meals : List<Meals>
)