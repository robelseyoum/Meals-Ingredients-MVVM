package com.robelseyoum3.mealsproject.view.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.robelseyoum3.mealsproject.R
import com.robelseyoum3.mealsproject.common.Constants
import com.robelseyoum3.mealsproject.model.mainallcategories.CategoriesSource
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface
import com.robelseyoum3.mealsproject.network.RetrofitInstances
import kotlinx.android.synthetic.main.fragment_first.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryRequest = RetrofitInstances().retrofitInstances.create(CategoryRequestInterface::class.java)
        val call = categoryRequest.getCategories()

        call.enqueue(object : Callback<CategoriesSource> {

            override fun onFailure(call: Call<CategoriesSource>, t: Throwable) {
            }

            override fun onResponse(call: Call<CategoriesSource>, response: Response<CategoriesSource>) {
                val res = response.body()
                Log.d("Categories Name", ""+res!!.categories[0]!!.strCategory)
                categoriesAdapterData(res)
            }
        })
    }

    fun categoriesAdapterData(categoriesSource: CategoriesSource){

        val adaptor = CategoriesAdaptor(categoriesSource, object : OnCategoryClickListener{
            override fun categoryMealClicked(categoryName: String) {
                Log.d("ClickCategories Name", ""+categoryName)
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

        val context = activity?.applicationContext
        fragmetTransaction?.replace(R.id.fragment_container_from_main, secondFragment)
            ?.addToBackStack(null)
            ?.commit()
    }


}
