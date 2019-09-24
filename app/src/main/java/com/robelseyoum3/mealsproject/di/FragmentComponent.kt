package com.robelseyoum3.mealsproject.di

import com.robelseyoum3.mealsproject.view.fragments.firstfragment.FirstFragment
import com.robelseyoum3.mealsproject.view.fragments.secondfragment.SecondFragment
import com.robelseyoum3.mealsproject.view.fragments.thirdfragment.ThirdFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(FragmentModule::class, NetworkModule::class))
interface FragmentComponent {


    fun inject(firstFragment: FirstFragment)
    fun inject(secondFragment: SecondFragment)
    fun inject(thirdFragment: ThirdFragment)


}
