package com.robelseyoum3.mealsproject.common

import android.content.Context
import android.net.ConnectivityManager


fun Context.isConnectedToNetwork(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
}

//
//private fun checkInternet(): Boolean {
//    val connectivityManager = this?.getSystemService(
//        Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    val activeNetworkInfo = connectivityManager.activeNetworkInfo
//    return activeNetworkInfo != null && activeNetworkInfo.isConnected
//}






