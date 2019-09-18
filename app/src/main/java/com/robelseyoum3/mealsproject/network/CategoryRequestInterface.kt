package com.robelseyoum3.mealsproject.network

import com.robelseyoum3.mealsproject.common.Constants
import com.robelseyoum3.mealsproject.model.mainallcategories.CategoriesSource
import com.robelseyoum3.mealsproject.model.specificcategries.MealsSource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryRequestInterface {


    @GET(Constants.CATEGORIES_VALUE)
    fun getCategories(): Call<CategoriesSource>

    @GET("filter.php")
    fun getCategoryByName(@Query("c") category: String?): Call<MealsSource>



    /*
    @GET("lookup.php")
    fun getCategoryByName(@Query("i") category: String?): Call<MealsSource>


     */



//https://www.themealdb.com/api/json/v1/1/categories.php
//https://www.themealdb.com/api/json/v1/1/filter.php?c=Chicken
//https://www.themealdb.com/api/json/v1/1/lookup.php?i=52940


}