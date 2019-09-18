package com.robelseyoum3.mealsproject.view.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.robelseyoum3.mealsproject.R
import com.robelseyoum3.mealsproject.common.Constants
import com.robelseyoum3.mealsproject.model.specificcategries.MealsSource
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface
import com.robelseyoum3.mealsproject.network.RetrofitInstances
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryName = arguments?.getString(Constants.CATEGORY_NAME)

        val mealRequest = RetrofitInstances().retrofitInstances.create(CategoryRequestInterface::class.java)
        val call = mealRequest.getCategoryByName(categoryName)

        call.enqueue(object : Callback<MealsSource>{
            override fun onFailure(call: Call<MealsSource>, t: Throwable) {
            }
            override fun onResponse(call: Call<MealsSource>, response: Response<MealsSource>) {
                val res = response.body()
                Log.d("Specific Cat Name", ""+res!!.meals[3]!!.idMeal)
            }
        })




    }


}