package com.robelseyoum3.mealsproject.viewmodel.allcategorymealviewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robelseyoum3.mealsproject.model.mainallcategories.BaseMealDatabase
import com.robelseyoum3.mealsproject.model.mainallcategories.Categories
import com.robelseyoum3.mealsproject.model.mainallcategories.CategoriesSource
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MealViewModel  @Inject constructor
    (private val categoryRequestInterface: CategoryRequestInterface, application: Application) : ViewModel(){


    private var allCategoryMutableData:  MutableLiveData<CategoriesSource>? = MutableLiveData()
    private var allCategoryDBMutableData:  MutableLiveData<List<Categories>>? = MutableLiveData()

    private var progressbarMutableData:  MutableLiveData<Boolean>? = MutableLiveData()
    private var errorMessagePage: MutableLiveData<Boolean>? = MutableLiveData()

    var showSuccess: MutableLiveData<Boolean> = MutableLiveData()
    var showDbSuccess: MutableLiveData<Boolean> = MutableLiveData()

    var compositeDisposable = CompositeDisposable() //we can add several observable

    var categoryDAO = BaseMealDatabase.getDatabase(application)?.CategoriesDAO() // database return


    //  fun getCategoryName() = catName

    fun getAllMealData(){

        progressbarMutableData?.value = true

        Log.i(TAG, "GET Cake result ")
        //this one is created at di.NetworkModule/fun provideClientInterface(retrofit: Retrofit) = retrofit.create(CakeRequestInterface::class.java)

        val call = categoryRequestInterface.getAllCategories()

        compositeDisposable.add(

            call.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        )
    }


    private fun handleResponse(result: CategoriesSource) {

        var res = result
        allCategoryMutableData?.value = res

        progressbarMutableData?.value = false
        errorMessagePage?.value = false

        //telling the database is connection is success
        showSuccess.value = true

        for (i in result.categories){
            addToDb(i)
        }
        //addToDb(res)

    }

    private fun handleError(error: Throwable) {
        Log.d("MainCat Error ", ""+error.message)
        errorMessagePage?.value = true
        progressbarMutableData?.value = false

        //telling the database is connection is success
        showSuccess.value = false
    }

    private fun addToDb(result: Categories) {

        compositeDisposable.add(
            categoryDAO!!.insertCategory(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {showDbSuccess.value = true}
        )
    }

    fun getAllDBCategories(){
        compositeDisposable.add(
            categoryDAO!!.getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                            cake -> allCategoryDBMutableData!!.value = cake
                    },{}
                )
        )
    }

    fun returnProgressBar(): MutableLiveData<Boolean>?{
        return progressbarMutableData
    }


    fun retunAllMealResult(): MutableLiveData<CategoriesSource>?{
        progressbarMutableData?.value = true
        return allCategoryMutableData
    }

    fun returnDBResult(): MutableLiveData<List<Categories>>?{
        return allCategoryDBMutableData
    }

    fun returnErrorResult(): MutableLiveData<Boolean>?{
        return errorMessagePage
    }


    companion object{
        const val TAG = "MealViewModel"
    }


    fun onDestroy() {
        compositeDisposable.clear()
    }


    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "ViewModel Destroyed")
    }
}