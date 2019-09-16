package com.robelseyoum3.mealsproject.model.maincategories


import com.google.gson.annotations.SerializedName

data class Categories (

	@SerializedName("idCategory") val idCategory : Int,
	@SerializedName("strCategory") val strCategory : String,
	@SerializedName("strCategoryThumb") val strCategoryThumb : String,
	@SerializedName("strCategoryDescription") val strCategoryDescription : String
)