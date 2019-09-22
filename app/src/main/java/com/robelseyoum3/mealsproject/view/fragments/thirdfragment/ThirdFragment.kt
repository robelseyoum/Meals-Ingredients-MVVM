package com.robelseyoum3.mealsproject.view.fragments.thirdfragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.robelseyoum3.mealsproject.R
import com.robelseyoum3.mealsproject.common.Constants
import com.robelseyoum3.mealsproject.di.DaggerFragmentComponent
import com.robelseyoum3.mealsproject.di.FragmentModule
import com.robelseyoum3.mealsproject.di.NetworkModule
import com.robelseyoum3.mealsproject.model.mealdetails.MealDetailSource
import com.robelseyoum3.mealsproject.model.specificcategries.Meals
import com.robelseyoum3.mealsproject.view.fragments.secondfragment.OnSpecificCategoryClickListener
import com.robelseyoum3.mealsproject.view.fragments.secondfragment.SpecificCategoriesAdapter
import com.robelseyoum3.mealsproject.viewmodel.detailmealsviewmodel.DetailMealViewModel
import com.robelseyoum3.mealsproject.viewmodel.detailmealsviewmodel.DetailMealViewModelFactory
import com.robelseyoum3.mealsproject.viewmodel.detailmealsviewmodel.DetailMealViewModel_Factory
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_third.*
import javax.inject.Inject


class ThirdFragment : Fragment() {


    @Inject
    lateinit var detailMealViewModelFactory: DetailMealViewModelFactory
    lateinit var viewModel: DetailMealViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        DaggerFragmentComponent.builder()
        .networkModule(NetworkModule(activity!!.application))
        .fragmentModule(FragmentModule())
        .build()
        .inject(this)

        val categoryID = arguments?.getString(Constants.CATEGORY_ID)

       // Log.d("Category_ID ThirdFrag", ""+categoryID)
        viewModel = ViewModelProviders.of(this, detailMealViewModelFactory).get(DetailMealViewModel::class.java)

        viewModel.getAllMealDetial(categoryID)

        viewModel.retunAllSpecificCatagoryResult()?.observe(this, object :Observer<MealDetailSource>{
            override fun onChanged(t: MealDetailSource?) {
                Log.d("MealDetial 3Cat ", ""+t!!.meals[3]!!.strArea)
                categoriesAdapterData(t.meals)
            }

        })

    }

    fun categoriesAdapterData(mealDetials: List<com.robelseyoum3.mealsproject.model.mealdetails.Meals> ){

        val adaptor = MealDetailAdapter(mealDetials)
        rvListThird.layoutManager = LinearLayoutManager(activity?.applicationContext)
        rvListThird.adapter = adaptor
    }


    companion object{
        const val TAG = "ThirdFragment - Roba"
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }



}
