package com.robelseyoum3.mealsproject.di

import com.robelseyoum3.mealsproject.view.fragments.firstfragment.FirstFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(FragmentModule::class, NetworkModule::class))
interface FragmentComponent {
    fun inject(firstFragment: FirstFragment)

}