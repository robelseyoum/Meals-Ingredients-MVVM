package com.robelseyoum3.mealsproject.view.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robelseyoum3.mealsproject.R
import com.robelseyoum3.mealsproject.model.mainallcategories.Categories
import com.robelseyoum3.mealsproject.model.mainallcategories.CategoriesSource
import com.robelseyoum3.mealsproject.model.specificcategries.MealsSource
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.categories_rows.view.*


class CategoriesAdaptor(private val categoriesSource: CategoriesSource, private val listener: OnCategoryClickListener ) :
RecyclerView.Adapter<CategoriesAdaptor.CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.categories_rows, parent, false))
    }

    override fun getItemCount(): Int {
        return categoriesSource.categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Picasso.get().load(categoriesSource.categories[position].strCategoryThumb).into(holder.categoryImage)
        holder.categoryName.text = categoriesSource.categories[position].strCategory

        holder.bind(categoriesSource.categories[position], listener)
    }


    class CategoryViewHolder (view: View): RecyclerView.ViewHolder(view){

        val categoryImage = view.category_image
        val categoryName = view.category_name

        fun bind(categories: Categories, listener: OnCategoryClickListener){
            itemView.setOnClickListener {
                //listener.categoryMealClicked(categories.categories[adapterPosition].strCategory)
                listener.categoryMealClicked(categories.strCategory)
            }
        }

    }
}


interface OnCategoryClickListener {
    fun categoryMealClicked(string: String)
}