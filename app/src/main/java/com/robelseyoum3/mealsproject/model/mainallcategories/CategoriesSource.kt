package com.robelseyoum3.mealsproject.model

import com.google.gson.annotations.SerializedName
import com.robelseyoum3.mealsproject.model.maincategories.Categories

data class CategoriesSource (
	@SerializedName("categories") val categories : List<Categories>
)