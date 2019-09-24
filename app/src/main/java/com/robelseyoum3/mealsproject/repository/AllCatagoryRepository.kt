package com.robelseyoum3.mealsproject.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.robelseyoum3.mealsproject.model.mainallcategories.BaseMealDatabase
import com.robelseyoum3.mealsproject.model.mainallcategories.Categories
import com.robelseyoum3.mealsproject.model.mainallcategories.CategoriesSource
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject


class AllCatagoryRepository
@Inject constructor(private val categoryRequestInterface: CategoryRequestInterface, application: Application): Application(){

    var categoryDAO = BaseMealDatabase.getDatabase(application)?.CategoriesDAO() // database return


    fun getAllCategories(): Observable<CategoriesSource>{
            return categoryRequestInterface.getAllCategories()
    }

    fun addCategoreisToDB(addtoCategoryRepo: Categories): Completable{
        return categoryDAO!!.insertAllCategory(addtoCategoryRepo)
    }

    fun getAllCategoriesFromDB(): Flowable<List<Categories>>{
            return categoryDAO!!.getAllCategories()
    }
}



