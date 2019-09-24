package com.robelseyoum3.mealsproject.di

import android.app.Application
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface
import com.robelseyoum3.mealsproject.repository.AllCatagoryRepository
import com.robelseyoum3.mealsproject.repository.DetialMealRepository
import com.robelseyoum3.mealsproject.repository.SpecificCatagoryRepository
import com.robelseyoum3.mealsproject.viewmodel.allcategorymealviewmodel.MealViewModelFactory
import com.robelseyoum3.mealsproject.viewmodel.detailmealsviewmodel.DetailMealViewModelFactory
import com.robelseyoum3.mealsproject.viewmodel.specificategoriesviewmodel.SpecificCategoryViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class FragmentModule {


    @Provides
    @Singleton
    fun provideMealViewModelFactory(allCatagoryRepository: AllCatagoryRepository)
    : MealViewModelFactory {
        return MealViewModelFactory(allCatagoryRepository)
    }

    @Provides
    @Singleton
    fun provideSpecificCategoryViewModelFactory(specificCatagoryRepository: SpecificCatagoryRepository)
    : SpecificCategoryViewModelFactory {
        return SpecificCategoryViewModelFactory(specificCatagoryRepository)
    }


    @Provides
    @Singleton
    fun provideDetailMealViewModelFactory(detailMealRepository: DetialMealRepository)
    : DetailMealViewModelFactory {
        return DetailMealViewModelFactory(detailMealRepository)
    }

   /*
    @Provides
    fun provideId() = id

*/
}



/*

    @Module
    class RepositoryModule {

        @Provides
        @Singleton
        fun provideTeamViewModelFactory(teamRepository: TeamRepository): TeamModelViewFactory{
            return TeamModelViewFactory(teamRepository)
        }
    }

*/