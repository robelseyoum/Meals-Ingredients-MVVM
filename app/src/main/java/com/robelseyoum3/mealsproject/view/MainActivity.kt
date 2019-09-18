package com.robelseyoum3.mealsproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robelseyoum3.mealsproject.R
import com.robelseyoum3.mealsproject.view.fragments.FirstFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment()
    }

    private fun addFragment(){

        var fragmentManager = supportFragmentManager
        var fragmetTransaction = fragmentManager.beginTransaction()

        fragmetTransaction.add(R.id.fragment_container_from_main, FirstFragment())
            //  .addToBackStack(null)
            .commit()
    }
}

//fragment_container_from_main
