package com.robelseyoum3.mealsproject.model.mainallcategories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
interface CategoriesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategory(categories: Categories): Completable


    @Query("Select * from allCategory_table")
    fun getAllCategories(): Flowable< List<Categories>>


}

