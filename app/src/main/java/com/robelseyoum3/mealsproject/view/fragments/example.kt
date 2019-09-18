package com.robelseyoum3.mealsproject.view.fragments

import com.robelseyoum3.mealsproject.R
import com.robelseyoum3.mealsproject.view.MainActivity


//package com.example.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
//import kotlinx.android.synthetic.main.fragment_example.*

class ExampleFragment: Fragment() {

/*
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(MainActivity.COMBINED_LIFECYCLE,MainActivity.FRAGMENT+ "oncreateview")
        return inflater.inflate(R.layout.fragment_example,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_go.setOnClickListener{
            goToNextFragment()
        }
    }

    private fun goToNextFragment(){
        val fragmentManager = activity?.supportFragmentManager
        val transaction = fragmentManager?.beginTransaction()
        val args = Bundle()
        args.putInt("number",10)
        val exampleFragment2 = ExampleFragment2()
        exampleFragment2.arguments = args
        val context = activity?.applicationContext
        transaction?.replace(R.id.fragmentContainer,exampleFragment2)
            ?.addToBackStack(null)
            ?.commit()
    }

*/

}

/*
package com.example.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_example_fragment2.*


class ExampleFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_example_fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val int = arguments?.getInt("number")
        tv_fragment2.text = int.toString()
    }

}
 */
