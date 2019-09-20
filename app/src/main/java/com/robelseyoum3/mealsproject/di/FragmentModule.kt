package com.robelseyoum3.mealsproject.di

import android.app.Application
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface
import com.robelseyoum3.mealsproject.viewmodel.MealViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class FragmentModule {
    @Provides
    @Singleton
    fun provideMealViewModelFactory(categoryRequestInterface: CategoryRequestInterface, application: Application)
    : MealViewModelFactory {
        return MealViewModelFactory(categoryRequestInterface, application)
    }
}

