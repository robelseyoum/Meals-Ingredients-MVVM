package com.robelseyoum3.mealsproject.viewmodel.specificategoriesviewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robelseyoum3.mealsproject.model.mainallcategories.BaseMealDatabase
import com.robelseyoum3.mealsproject.model.specificcategries.Meals
import com.robelseyoum3.mealsproject.model.specificcategries.MealsSource
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface
import com.robelseyoum3.mealsproject.repository.SpecificCatagoryRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SpecificCategoryViewModel(val specificCatagoryRepository: SpecificCatagoryRepository ) : ViewModel() {

    private var allSpecificCategorMutableData: MutableLiveData<MealsSource>? = MutableLiveData()
    private var allSpecificCategoDBrMutableData: MutableLiveData<List<Meals>> = MutableLiveData()

    private var progressbarMutableData:  MutableLiveData<Boolean>? = MutableLiveData()
    private var errorMessagePage: MutableLiveData<Boolean>? = MutableLiveData()

    var showSuccess: MutableLiveData<Boolean> = MutableLiveData()

    var showDbSuccess: MutableLiveData<Boolean> = MutableLiveData()

    var compositeDisposable = CompositeDisposable() //we can add several observable

   // var specificDAO = BaseMealDatabase.getDatabase(application)?.SpecificCatDAO() // database return


    fun getAllSpecificCategory(catName: String?){
        progressbarMutableData?.value = true
        Log.i(TAG, "Get specific category")

        //val call = categoryRequestInterface.getCategoryByName(catName)
        val call = specificCatagoryRepository.getAllSpecificCategories(catName)

        call.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError)
    }

    private fun handleResponse(result: MealsSource) {

        var res = result
        allSpecificCategorMutableData?.value = res

        progressbarMutableData?.value = false
        errorMessagePage?.value = false

        //telling the database is connection is success
        showSuccess.value = true

        for (i in result.meals){
            addToDb(i)
        }
        //addToDb(res)
    }

    private fun handleError(error: Throwable) {
        Log.d("2SpecCAt Error ", ""+error.message)
        errorMessagePage?.value = true
        progressbarMutableData?.value = false

        //telling the database is connection is success
        showSuccess.value = false
    }


    fun retunAllSpecificCatagoryResult(): MutableLiveData<MealsSource>?{
        progressbarMutableData?.value = true
        return allSpecificCategorMutableData
    }


    private fun addToDb(result: Meals) {

        compositeDisposable.add(
            specificCatagoryRepository!!.addSpecificCategoreisToDB(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {showDbSuccess.value = true}
        )
    }

    fun getAllSpecificDBCategories(){
        compositeDisposable.add(
            specificCatagoryRepository.getAllSpecificCategoriesFromDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                            category -> allSpecificCategoDBrMutableData!!.value = category
                    },{}
                )
        )
    }


    fun returnSpecificDBResult(): MutableLiveData<List<Meals>>? {
        return allSpecificCategoDBrMutableData
    }

    fun returnErrorResult(): MutableLiveData<Boolean>?{
        return errorMessagePage
    }

    fun returnProgressBar(): MutableLiveData<Boolean>?{
        return progressbarMutableData
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "SpecificCategoryViewModel Destroyed")
    }


    companion object{
        const val TAG = "SpecificCatViewModel"
    }


    fun onDestroy() {
        compositeDisposable.clear()
    }

}

