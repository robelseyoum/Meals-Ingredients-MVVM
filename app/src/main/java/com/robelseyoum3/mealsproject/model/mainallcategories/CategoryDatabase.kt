package com.robelseyoum3.mealsproject.model.mainallcategories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Categories::class), version = 1, exportSchema = false)
abstract class CategoryDatabase: RoomDatabase() {

    abstract fun CategoriesDAO(): CategoriesDAO


    companion object{

        @Volatile
        private var INSTANCE: CategoryDatabase? = null

        fun getDatabase(context: Context): CategoryDatabase?{

            val temInstances = INSTANCE

            if(INSTANCE != null){
                return temInstances
            }

            synchronized(this){

                val instances =  Room.databaseBuilder(context.applicationContext,
                    CategoryDatabase::class.java, "allCategory_table")
                    .build()

                INSTANCE = instances
            }

            return INSTANCE
        }


    }
}
