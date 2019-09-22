package com.robelseyoum3.mealsproject.viewmodel.allcategorymealviewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface

class MealViewModelFactory
    (private val categoryRequestInterface: CategoryRequestInterface, private val application: Application)
    : ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MealViewModel(
            categoryRequestInterface,
            application) as T
    }
}

