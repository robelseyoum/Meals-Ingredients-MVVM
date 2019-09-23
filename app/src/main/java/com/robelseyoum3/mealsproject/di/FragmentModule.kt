package com.robelseyoum3.mealsproject.di

import android.app.Application
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface
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
    fun provideMealViewModelFactory(categoryRequestInterface: CategoryRequestInterface, application: Application)
    : MealViewModelFactory {
        return MealViewModelFactory(
            categoryRequestInterface,
            application
        )
    }

    @Provides
    @Singleton
    fun provideSpecificCategoryViewModelFactory(categoryRequestInterface: CategoryRequestInterface, application: Application)
    : SpecificCategoryViewModelFactory {
        return SpecificCategoryViewModelFactory(categoryRequestInterface, application)
    }


    @Provides
    @Singleton
    fun provideDetailMealViewModelFactory(categoryRequestInterface: CategoryRequestInterface, application: Application)
    : DetailMealViewModelFactory {
        return DetailMealViewModelFactory(categoryRequestInterface, application)
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