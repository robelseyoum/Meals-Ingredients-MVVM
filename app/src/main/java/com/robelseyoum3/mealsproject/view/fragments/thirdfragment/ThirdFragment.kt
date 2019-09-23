package com.robelseyoum3.mealsproject.view.fragments.thirdfragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.robelseyoum3.mealsproject.R
import com.robelseyoum3.mealsproject.common.Constants
import com.robelseyoum3.mealsproject.di.DaggerFragmentComponent
import com.robelseyoum3.mealsproject.di.FragmentModule
import com.robelseyoum3.mealsproject.di.NetworkModule
import com.robelseyoum3.mealsproject.model.mealdetails.MealDetailSource
import com.robelseyoum3.mealsproject.model.specificcategries.MealsSource
import com.robelseyoum3.mealsproject.viewmodel.detailmealsviewmodel.DetailMealViewModel
import com.robelseyoum3.mealsproject.viewmodel.detailmealsviewmodel.DetailMealViewModelFactory
import io.reactivex.Observable
import io.reactivex.Observer
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

        val mealID = arguments?.getString(Constants.CATEGORY_ID)

       Log.d("MealID ThirdFrag", ""+mealID)

        viewModel = ViewModelProviders.of(this, detailMealViewModelFactory).get(DetailMealViewModel::class.java)

        viewModel.getAllMealDetial(mealID?.toInt())

        viewModel.returnDetailMealResult()?.observe(this, object :androidx.lifecycle.Observer<MealDetailSource>{
            override fun onChanged(t: MealDetailSource?) {
                categoriesAdapterData(t!!.meals)
            }
        })
    }

    fun categoriesAdapterData(mealDetials: List<com.robelseyoum3.mealsproject.model.mealdetails.Meals>){

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
