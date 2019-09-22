package com.robelseyoum3.mealsproject.view.fragments.thirdfragment

import android.view.LayoutInflater
import android.view.PixelCopy
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robelseyoum3.mealsproject.R
import com.robelseyoum3.mealsproject.model.mealdetails.Meals
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_mail_rows.view.*

class MealDetailAdapter(private val mealDetail: List<Meals>)
    :RecyclerView.Adapter<MealDetailAdapter.MealDetialViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealDetialViewHolder {
        return MealDetialViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.detail_mail_rows, parent, false))
    }

    override fun getItemCount(): Int {
        return mealDetail.size
    }

    override fun onBindViewHolder(holder: MealDetialViewHolder, position: Int) {

        Picasso.get().load(mealDetail[position].strMealThumb).into(holder.detialMealImage)
        holder.detialMealName.text = mealDetail[position].strMeal
        holder.detialMealDescrip.text = mealDetail[position].strInstructions
    }

    class MealDetialViewHolder(view: View): RecyclerView.ViewHolder(view){
        val detialMealImage = view.tv_image_detail
        val detialMealName = view.tv_title_detail
        val detialMealDescrip = view.tv_description_detail
    }
}

