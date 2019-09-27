package com.robelseyoum3.mealsproject.viewmodel.detailmealsviewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robelseyoum3.mealsproject.model.mealdetails.MealDetailSource
import com.robelseyoum3.mealsproject.model.mealdetails.Meals
import com.robelseyoum3.mealsproject.repository.DetialMealRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailMealViewModel (val detailMealRepository: DetialMealRepository) : ViewModel() {

    //private var detailMealMutableData: MutableLiveData<MealDetailSource>?  = MutableLiveData()
    private var detailMealMutableData: MutableLiveData<List<Meals>>?  = MutableLiveData()

    private var detailMealDBMutableData: MutableLiveData<Meals>? = MutableLiveData()

    private var progressbarMutableData:  MutableLiveData<Boolean>? = MutableLiveData()
    private var errorMessagePage: MutableLiveData<Boolean>? = MutableLiveData()

    var showSuccess: MutableLiveData<Boolean> = MutableLiveData()
    var showDbSuccess: MutableLiveData<Boolean> = MutableLiveData()

    var compositeDisposable = CompositeDisposable() //we can add several observable

 //   var detailDAO = BaseMealDatabase.getDatabase(application)?.DetialMealDAO() // database return

    fun getAllMealDetial(mealID: Int?){

        progressbarMutableData?.value = true
        Log.i(TAG, " 3MealID  "+mealID)

       // val call = categoryRequestInterface.getCategoryByID(mealID)
        val call = detailMealRepository.getAllMealDetails(mealID)
        compositeDisposable.add(
        call.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError))
    }


    private fun handleResponse(result: MealDetailSource) {

        var res = result

        detailMealMutableData?.value = result.meals

        progressbarMutableData?.value = false
        errorMessagePage?.value = false

        //telling the database is connection is success
        showSuccess.value = true

        for (i in result.meals){  //for(i in artists.arti
            addToDb(i)
        }
//        addToDb(result)
    }


    private fun handleError(error: Throwable) {

        Log.d("3SpecD Error ", ""+error.message)
        errorMessagePage?.value = true
        progressbarMutableData?.value = false

        //telling the database is connection is success
        showSuccess.value = false

    }


    fun returnDetailMealResult(): MutableLiveData<List<Meals>>? {
        progressbarMutableData?.value = true
        return detailMealMutableData
    }

    private fun addToDb(result: Meals) {
        compositeDisposable.add(
            //detailDAO!!.insertDetail(result)
            detailMealRepository!!.addMealDetailsToDB(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {showDbSuccess.value = true}
        )
    }

    fun getAllDetailsDBMeals(){
        compositeDisposable.add(
            //detailDAO!!.getAllDetailMeal()
            detailMealRepository.getAllMealDetailsFromDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                            dbmeals -> detailMealDBMutableData!!.value = dbmeals
                    },{}
                )
        )
    }


    fun returnDetailDBResult(): MutableLiveData<Meals>?{
        return detailMealDBMutableData
    }

    fun returnProgressBar(): MutableLiveData<Boolean>?{
        return progressbarMutableData
    }

    fun returnErrorResult(): MutableLiveData<Boolean>? {
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
