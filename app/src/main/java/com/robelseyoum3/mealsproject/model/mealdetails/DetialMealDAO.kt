package com.robelseyoum3.mealsproject.model.mealdetails

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface DetialMealDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(meals: com.robelseyoum3.mealsproject.model.mealdetails.Meals): Completable


    @Query("Select * from detailMeal_table")
    fun getAllDetailMeal(): Flowable<com.robelseyoum3.mealsproject.model.mealdetails.Meals>
}

