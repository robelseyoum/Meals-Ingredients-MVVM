package com.robelseyoum3.mealsproject.model

import com.google.gson.annotations.SerializedName


data class CategoriesAccess (
	@SerializedName("categories") val categories : List<Categories>
)