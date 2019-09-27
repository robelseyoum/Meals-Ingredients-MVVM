package com.robelseyoum3.mealsproject.common

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService

class Utils {

    companion object {

         fun checkInternet(context: Context): Boolean {
            val connectivityManager = context.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    }
}