package com.robelseyoum3.mealsproject.repository

import android.app.Application
import com.robelseyoum3.mealsproject.model.mainallcategories.BaseMealDatabase
import com.robelseyoum3.mealsproject.model.mainallcategories.Categories
import com.robelseyoum3.mealsproject.model.mainallcategories.CategoriesSource
import com.robelseyoum3.mealsproject.model.specificcategries.Meals
import com.robelseyoum3.mealsproject.model.specificcategries.MealsSource
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class SpecificCatagoryRepository
    @Inject constructor(private val categoryRequestInterface: CategoryRequestInterface, application: Application): Application(){

    var specificcategoryDAO = BaseMealDatabase.getDatabase(application)?.SpecificCatDAO() //database return

    fun getAllSpecificCategories(catName: String?): Observable<MealsSource> {
        return categoryRequestInterface.getCategoryByName(catName)
    }

    fun addSpecificCategoreisToDB(result: Meals): Completable {
        return specificcategoryDAO!!.insertCategory(result)
    }

    fun getAllSpecificCategoriesFromDB(): Flowable<List<Meals>> {
        return specificcategoryDAO!!.getSpecificCategory()
    }
}

