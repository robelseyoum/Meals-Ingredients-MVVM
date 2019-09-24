package com.robelseyoum3.mealsproject.viewmodel.allcategorymealviewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface
import com.robelseyoum3.mealsproject.repository.AllCatagoryRepository

/*
class MealViewModelFactory
    (private val categoryRequestInterface: CategoryRequestInterface, private val application: Application)
    : ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MealViewModel(
            categoryRequestInterface,
            application) as T
    }
}
*/

class MealViewModelFactory
    (private val allCatagoryRepository: AllCatagoryRepository)
    : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MealViewModel(allCatagoryRepository) as T
    }
}


