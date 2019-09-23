package com.robelseyoum3.mealsproject.model.mainallcategories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.robelseyoum3.mealsproject.model.mealdetails.DetialMealDAO
import com.robelseyoum3.mealsproject.model.specificcategries.SpecificCatDAO

@Database(entities = arrayOf(Categories::class, com.robelseyoum3.mealsproject.model.mealdetails.Meals::class, com.robelseyoum3.mealsproject.model.specificcategries.Meals::class), version = 3, exportSchema = false)
abstract class BaseMealDatabase: RoomDatabase() {

    abstract fun CategoriesDAO(): CategoriesDAO
    abstract fun SpecificCatDAO(): SpecificCatDAO
    abstract fun DetialMealDAO(): DetialMealDAO


    companion object{

        @Volatile
        private var INSTANCE: BaseMealDatabase? = null

        fun getDatabase(context: Context): BaseMealDatabase?{

            val temInstances = INSTANCE

            if(INSTANCE != null){
                return temInstances
            }

            synchronized(this){

                val instances =  Room.databaseBuilder(context.applicationContext,
                    BaseMealDatabase::class.java, "Base_Meal_table")
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instances
            }

            return INSTANCE
        }


    }
}
