package com.robelseyoum3.mealsproject.model.specificcategries

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface SpecificCatDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(meals: Meals): Completable


    @Query("Select * from specificMeal_table")
    fun getSpecificCategory(): Flowable<List<Meals>>
}