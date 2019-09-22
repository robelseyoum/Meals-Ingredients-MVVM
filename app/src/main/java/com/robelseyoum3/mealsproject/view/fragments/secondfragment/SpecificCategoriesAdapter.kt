package com.robelseyoum3.mealsproject.view.fragments.secondfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robelseyoum3.mealsproject.R
import com.robelseyoum3.mealsproject.model.specificcategries.Meals
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.specific_categories_rows.view.*

class SpecificCategoriesAdapter(private val specificCategoryMeal: List<Meals>, private val listener: OnSpecificCategoryClickListener) :
    RecyclerView.Adapter<SpecificCategoriesAdapter.SpecificCategoryViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificCategoryViewHolder {
        return SpecificCategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.specific_categories_rows, parent, false))
    }

    override fun getItemCount(): Int {
        return specificCategoryMeal.size
    }

    override fun onBindViewHolder(holder: SpecificCategoryViewHolder, position: Int) {

        Picasso.get().load(specificCategoryMeal[position].strMealThumb).into(holder.specificCategoryImages)
        holder.specificCategoryNames.text = specificCategoryMeal[position].strMeal
        // holder.bind(categoriesSource[position], listener)
        holder.bind(specificCategoryMeal[position], listener)

    }

    class SpecificCategoryViewHolder(view: View): RecyclerView.ViewHolder(view){
        val specificCategoryImages = view.specific_category_image
        val specificCategoryNames = view.specific_category_name

        fun bind(meals: Meals, listener: OnSpecificCategoryClickListener){
            itemView.setOnClickListener {
                listener.specificCategoryMealClicked(meals.idMeal.toString())
            }
        }


    }
}

interface OnSpecificCategoryClickListener {
    fun specificCategoryMealClicked(string: String)
}

