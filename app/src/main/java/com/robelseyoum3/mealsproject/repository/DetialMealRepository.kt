package com.robelseyoum3.mealsproject.repository

import android.app.Application
import com.robelseyoum3.mealsproject.model.mainallcategories.BaseMealDatabase
import com.robelseyoum3.mealsproject.model.mealdetails.MealDetailSource
import com.robelseyoum3.mealsproject.model.mealdetails.Meals
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class DetialMealRepository
    @Inject constructor(private val categoryRequestInterface: CategoryRequestInterface, application: Application): Application(){

    private val detailMealDAO =  BaseMealDatabase.getDatabase(application)?.DetialMealDAO() //database


    fun getAllMealDetails(mealID: Int?): Observable<MealDetailSource> {
        return categoryRequestInterface.getCategoryByID(mealID)
    }

    fun addMealDetailsToDB(result: Meals): Completable {
        return detailMealDAO!!.insertDetail(result)
    }

    fun getAllMealDetailsFromDB(): Flowable<Meals> {
        return detailMealDAO!!.getAllDetailMeal()
    }



}

