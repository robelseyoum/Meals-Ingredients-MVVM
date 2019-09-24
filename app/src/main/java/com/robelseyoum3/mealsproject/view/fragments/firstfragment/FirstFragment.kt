package com.robelseyoum3.mealsproject.view.fragments.firstfragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.robelseyoum3.mealsproject.R
import com.robelseyoum3.mealsproject.common.Constants
import com.robelseyoum3.mealsproject.di.DaggerFragmentComponent
import com.robelseyoum3.mealsproject.di.FragmentModule
import com.robelseyoum3.mealsproject.di.NetworkModule
import com.robelseyoum3.mealsproject.model.mainallcategories.Categories
import com.robelseyoum3.mealsproject.model.mainallcategories.CategoriesSource
import com.robelseyoum3.mealsproject.view.fragments.secondfragment.SecondFragment
import com.robelseyoum3.mealsproject.viewmodel.allcategorymealviewmodel.MealViewModel
import com.robelseyoum3.mealsproject.viewmodel.allcategorymealviewmodel.MealViewModelFactory
import kotlinx.android.synthetic.main.fragment_first.*
import javax.inject.Inject

class FirstFragment : Fragment() {

    @Inject
    lateinit var mealViewModelFactory: MealViewModelFactory
    lateinit var viewModel: MealViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            DaggerFragmentComponent.builder()
                .networkModule(NetworkModule(activity!!.application))
                .fragmentModule(FragmentModule())
                .build()
                .inject(this)


        viewModel = ViewModelProviders.of(this, mealViewModelFactory).get(MealViewModel::class.java)


       //viewModel.getAllMealData(checknetWork())
        viewModel.getAllMealData()


        viewModel.retunAllMealResult()?.observe(this, object :Observer<CategoriesSource>{

            override fun onChanged(t: CategoriesSource?) {
                Log.d("retunAllMealResultName", ""+t!!.categories[0].strCategory)

                categoriesAdapterData(t.categories)
            }
        })

//        viewModel.getAllDBCategories()
//
//        viewModel.returnDBResult()?.observe(this, object : Observer<List<Categories>>{
//            override fun onChanged(t: List<Categories>) {
//                //Log.d("Categories Name", ""+t!!.categories[0]!!.strCategory)
//                categoriesAdapterData(t)
//            }
//        })


        viewModel.returnProgressBar()?.observe(this, object : Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
                if(t==true){
                    progress_id_main.visibility = View.VISIBLE
                }else{
                    progress_id_main.visibility = View.GONE
                }
            }
        })


        viewModel.returnErrorResult()?.observe(this, object : Observer<Boolean>{
            override fun onChanged(t: Boolean?) {

                if(t == true){
                    Toast.makeText(activity,"Show error page",Toast.LENGTH_SHORT).show()
                    include_error_msg.visibility = View.VISIBLE
                }else{
                    include_error_msg.visibility = View.GONE
                }
            }

        })

        viewModel.showDbSuccess.observe(this, Observer {
            if (it == true){
                Toast.makeText(context,"got Meal Database successfully",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Something went wrong with db",Toast.LENGTH_SHORT).show()
            }
        })


    }

    fun categoriesAdapterData(categoriesSource: List<Categories> ){

        val adaptor = CategoriesAdaptor(
            categoriesSource,
            object : OnCategoryClickListener {
                override fun categoryMealClicked(categoryName: String) {
                    Log.d("ClickAllCat First", "" + categoryName)
                    addFragment(categoryName)
                }

            })

        rvList.layoutManager = LinearLayoutManager(activity?.applicationContext)
        rvList.adapter = adaptor
    }

    private fun addFragment(categoryName: String){

        var fragmentManager = activity?.supportFragmentManager
        var fragmetTransaction = fragmentManager?.beginTransaction()
        var args = Bundle()

        args.putString(Constants.CATEGORY_NAME, categoryName)
        val secondFragment = SecondFragment()
        secondFragment.arguments = args

        fragmetTransaction?.replace(R.id.fragment_container_from_main, secondFragment)
            ?.addToBackStack(null)
            ?.commit()
    }


    companion object{
        const val TAG = "FirstFragment - RobaZmaks"
    }

    override fun onDestroy() {
        super.onDestroy()
       viewModel.onDestroy()
    }





}
