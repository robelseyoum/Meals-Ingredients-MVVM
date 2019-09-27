package com.robelseyoum3.mealsproject.view.fragments.secondfragment


import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.robelseyoum3.mealsproject.R
import com.robelseyoum3.mealsproject.common.Constants
import com.robelseyoum3.mealsproject.common.Utils
import com.robelseyoum3.mealsproject.di.DaggerFragmentComponent
import com.robelseyoum3.mealsproject.di.FragmentModule
import com.robelseyoum3.mealsproject.di.NetworkModule
import com.robelseyoum3.mealsproject.model.mainallcategories.Categories
import com.robelseyoum3.mealsproject.model.specificcategries.Meals
import com.robelseyoum3.mealsproject.model.specificcategries.MealsSource
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface
import com.robelseyoum3.mealsproject.view.fragments.firstfragment.CategoriesAdaptor
import com.robelseyoum3.mealsproject.view.fragments.firstfragment.FirstFragment
import com.robelseyoum3.mealsproject.view.fragments.firstfragment.OnCategoryClickListener
//import com.robelseyoum3.mealsproject.network.RetrofitInstances
import com.robelseyoum3.mealsproject.view.fragments.thirdfragment.ThirdFragment
import com.robelseyoum3.mealsproject.viewmodel.allcategorymealviewmodel.MealViewModel
import com.robelseyoum3.mealsproject.viewmodel.specificategoriesviewmodel.SpecificCategoryViewModel
import com.robelseyoum3.mealsproject.viewmodel.specificategoriesviewmodel.SpecificCategoryViewModelFactory
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SecondFragment : Fragment() {

    @Inject
    lateinit var specificCategoryViewModelFactory: SpecificCategoryViewModelFactory
    lateinit var viewModel: SpecificCategoryViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerFragmentComponent.builder()
            .networkModule(NetworkModule(activity!!.application))
            .fragmentModule(FragmentModule())
            .build()
            .inject(this)

        val categoryName = arguments?.getString(Constants.CATEGORY_NAME)

        viewModel = ViewModelProviders.of(this, specificCategoryViewModelFactory).get(SpecificCategoryViewModel::class.java)


        if(Utils.checkInternet(activity!!.applicationContext)){
            viewModel.getAllSpecificCategory(categoryName)
            Log.d(TAG, "READ FROM API")
        }else{
            viewModel.getAllSpecificDBCategories()
            Log.d(TAG, "READ FROM DATABASE")
        }


//        viewModel.getAllSpecificCategory(categoryName)


        viewModel.retunAllSpecificCatagoryResult()?.observe(this, object :Observer<MealsSource>{
            override fun onChanged(t: MealsSource?) {

                Log.d("Specific 2Cat ", ""+t!!.meals.size)

                categoriesAdapterData(t.meals)
            }
        })

//        viewModel.getAllSpecificDBCategories()

        viewModel.returnSpecificDBResult()?.observe(this, object : Observer<List<Meals>>{
            override fun onChanged(t: List<Meals>) {
                categoriesAdapterData(t)
            }
        })


        viewModel.returnProgressBar()?.observe(this, object : Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
                if(t==true){
                    progress_id_second.visibility = View.VISIBLE
                }else{
                    progress_id_second.visibility = View.GONE
                }
            }
        })


        viewModel.returnErrorResult()?.observe(this, object : Observer<Boolean>{
            override fun onChanged(t: Boolean?) {

                if(t == true){
                    Toast.makeText(activity,"Show error page", Toast.LENGTH_SHORT).show()
                    include_error_msg_2.visibility = View.VISIBLE
                }else{
                    include_error_msg_2.visibility = View.GONE
                }
            }

        })

        viewModel.showDbSuccess.observe(this, Observer {
            if (it == true){
                Toast.makeText(context,"got Meal Database successfully", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Something went wrong with db", Toast.LENGTH_SHORT).show()
            }
        })


    }

    fun categoriesAdapterData(categoriesSource: List<Meals> ){

        val adaptor = SpecificCategoriesAdapter(categoriesSource, object : OnSpecificCategoryClickListener{
            override fun specificCategoryMealClicked(categoryID: Int) {
            //  Log.d("2ClickSpecif CatID", "" + categoryID)
               addFragment(categoryID.toString())
            }
        })

        rvListSecond.layoutManager = GridLayoutManager(activity?.applicationContext, 2)
        //(activity?.applicationContext)
        rvListSecond.adapter = adaptor
    }


    private fun addFragment(categoryID: String){

        var fragmentManager = activity?.supportFragmentManager
        var fragmetTransaction = fragmentManager?.beginTransaction()
        var args = Bundle()

        args.putString(Constants.CATEGORY_ID, categoryID)

        val thirdFragment = ThirdFragment()
        thirdFragment.arguments = args

        fragmetTransaction?.replace(R.id.fragment_container_from_main, thirdFragment)
            ?.addToBackStack(null)
            ?.commit()
    }



    companion object{
        const val TAG = "SecondFragment"
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }



}
