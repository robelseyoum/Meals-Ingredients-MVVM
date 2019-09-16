package com.robelseyoum3.mealsproject.model.mainallcategories

import com.google.gson.annotations.SerializedName
import com.robelseyoum3.mealsproject.model.mainallcategories.Categories

data class CategoriesSource (
	@SerializedName("categories") val categories : List<Categories>
)