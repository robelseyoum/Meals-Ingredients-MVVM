package com.robelseyoum3.mealsproject.model.mainallcategories


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "allCategory_table")
data class Categories (
	@PrimaryKey
	@SerializedName("idCategory") val idCategory : Int,
	@SerializedName("strCategory") val strCategory : String,
	@SerializedName("strCategoryThumb") val strCategoryThumb : String,
	@SerializedName("strCategoryDescription") val strCategoryDescription : String

)