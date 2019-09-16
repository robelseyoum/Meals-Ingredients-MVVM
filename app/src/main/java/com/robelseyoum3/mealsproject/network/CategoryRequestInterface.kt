package com.robelseyoum3.mealsproject.network

import com.robelseyoum3.mealsproject.common.Constants
import com.robelseyoum3.mealsproject.model.mainallcategories.CategoriesSource
import retrofit2.Call
import retrofit2.http.GET

interface CategoryRequestInterface {



    /*

    @GET(Constants.CAKES)
    fun getCakes(): Observable<List<CakeResult>>
     */

    @GET(Constants.CATEGORIES_VALUE)
    fun getCategories(): Call<CategoriesSource>
    /*

//    const val BASE_URL_TEAM = "https://www.thesportsdb.com/api/v1/json/1/"
//    const val TEAMVALUE = "English Premier League"
//    const val TEAM_ID = "TEAM_ID"


    @GET("search_all_teams.php")
    fun getTeams(@Query("l") leagues: String): Call<TeamModel>

    //https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League

    //https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=133604

    @GET("lookupteam.php")
    fun getTeamDetails(@Query("id") teamID : String): Call<TeamDetailModel>
     */

//https://www.themealdb.com/api/json/v1/1/categories.php
//https://www.themealdb.com/api/json/v1/1/filter.php?c=Chicken
//https://www.themealdb.com/api/json/v1/1/lookup.php?i=52940




}