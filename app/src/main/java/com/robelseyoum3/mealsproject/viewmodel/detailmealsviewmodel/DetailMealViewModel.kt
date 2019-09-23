package com.robelseyoum3.mealsproject.viewmodel.detailmealsviewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robelseyoum3.mealsproject.model.mealdetails.MealDetailSource
import com.robelseyoum3.mealsproject.model.mealdetails.Meals
import com.robelseyoum3.mealsproject.model.specificcategries.MealsSource
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailMealViewModel  @Inject constructor
    (private val categoryRequestInterface: CategoryRequestInterface, application: Application) : ViewModel() {

    private var detailMealMutableData: MutableLiveData<MealDetailSource>?  = MutableLiveData()
    private var detailMealDBMutableData: MutableLiveData<List<Meals>>? = MutableLiveData()

    private var progressbarMutableData:  MutableLiveData<Boolean>? = MutableLiveData()
    private var errorMessagePage: MutableLiveData<Boolean>? = MutableLiveData()

    var showSuccess: MutableLiveData<Boolean> = MutableLiveData()
    var showDbSuccess: MutableLiveData<Boolean> = MutableLiveData()

    var compositeDisposable = CompositeDisposable() //we can add several observable


    fun getAllMealDetial(mealID: Int?){

        progressbarMutableData?.value = true
        Log.i(TAG, " 3MealID  "+mealID)

        val call = categoryRequestInterface.getCategoryByID(mealID)

        call.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError)
    }


    private fun handleResponse(result: MealDetailSource) {

        var res = result

        detailMealMutableData?.value = res

        progressbarMutableData?.value = false
        errorMessagePage?.value = false

        //telling the database is connection is success
        showSuccess.value = true

        for (i in result.meals){
            //addToDb(i)
        }
        //addToDb(res)
    }


    private fun handleError(error: Throwable) {

        Log.d("3SpecD Error ", ""+error.message)
        errorMessagePage?.value = true
        progressbarMutableData?.value = false

        //telling the database is connection is success
        showSuccess.value = false

    }


    fun returnDetailMealResult(): MutableLiveData<MealDetailSource>? {
        progressbarMutableData?.value = true
        return detailMealMutableData
    }


    fun returnErrorResult(): MutableLiveData<Boolean>?{
        return errorMessagePage
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "MealDetialViewModel Destroyed")
    }


    companion object{
        const val TAG = "MealDetialViewModel"
    }


    fun onDestroy() {
        compositeDisposable.clear()
    }

}
