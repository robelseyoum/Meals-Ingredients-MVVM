package com.robelseyoum3.mealsproject.view.fragments.thirdfragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.robelseyoum3.mealsproject.R
import com.robelseyoum3.mealsproject.common.Constants
import com.robelseyoum3.mealsproject.di.DaggerFragmentComponent
import com.robelseyoum3.mealsproject.di.FragmentModule
import com.robelseyoum3.mealsproject.di.NetworkModule
import com.robelseyoum3.mealsproject.model.mealdetails.MealDetailSource
import com.robelseyoum3.mealsproject.model.mealdetails.Meals
import com.robelseyoum3.mealsproject.viewmodel.detailmealsviewmodel.DetailMealViewModel
import com.robelseyoum3.mealsproject.viewmodel.detailmealsviewmodel.DetailMealViewModelFactory
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

        viewModel.getAllDetailsDBMeals()

        viewModel.returnDetailDBResult()?.observe(this, object : androidx.lifecycle.Observer<List<Meals>>{
            override fun onChanged(t: List<Meals>) {
                categoriesAdapterData(t)
            }
        })

        viewModel.returnProgressBar()?.observe(this, object : androidx.lifecycle.Observer<Boolean> {
            override fun onChanged(t: Boolean?) {
                if(t==true){
                    progress_id_third.visibility = View.VISIBLE
                }else{
                    progress_id_third.visibility = View.GONE
                }
            }
        })


        viewModel.returnErrorResult()?.observe(this, object : androidx.lifecycle.Observer<Boolean> {
            override fun onChanged(t: Boolean?) {

                if(t == true){
                    Toast.makeText(activity,"Show error page", Toast.LENGTH_SHORT).show()
                    include_error_msg_3.visibility = View.VISIBLE
                }else{
                    include_error_msg_3.visibility = View.GONE
                }
            }

        })

        viewModel.showDbSuccess.observe(this, androidx.lifecycle.Observer {
            if(it == true){
                Toast.makeText(context,"got Meal Database successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context,"Something went wrong with db", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun categoriesAdapterData(mealDetials: List<com.robelseyoum3.mealsproject.model.mealdetails.Meals>){

        val adaptor = MealDetailAdapter(mealDetials)
        rvListThird.layoutManager = LinearLayoutManager(activity?.applicationContext)
        rvListThird.adapter = adaptor
    }

    fun detailMeals()


    companion object{
        const val TAG = "ThirdFragment - Roba"
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }



}
