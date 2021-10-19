package com.pilltracker.movies.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.SupplicantState
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager


object NetworkUtils {

    fun isNetworkConnected(context: Context?): Boolean {

        try {
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            cm.activeNetwork?.let {
                val network = it
                val nc: NetworkCapabilities = cm.getNetworkCapabilities(network)!!
                return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)

            }?: kotlin.run {
                false
            }
        } catch (e: Exception){
            return false
        }
      return false
    }


    fun isInternetAvailable(context: Context): Boolean {

        try {
            val result: Boolean
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

            return result

        } catch (e : Exception){
            return false
        }

    }


    fun getConnectionInfo(context: Context): String? {

        var result: String? = null
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return null
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return null

        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> getWifiInfo(context)
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> getCellarInfo(context)
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> ""//getCellarInfo(context)
            else -> null
        }

        return result



    }

    private fun getCellarInfo(context: Context): String? {

        val mTelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        when (mTelephonyManager.networkType) {
            TelephonyManager.NETWORK_TYPE_GPRS,
            TelephonyManager.NETWORK_TYPE_EDGE,
            TelephonyManager.NETWORK_TYPE_CDMA,
            TelephonyManager.NETWORK_TYPE_1xRTT,
            TelephonyManager.NETWORK_TYPE_IDEN,
            TelephonyManager.NETWORK_TYPE_GSM
            -> return "2G"
            TelephonyManager.NETWORK_TYPE_UMTS,
            TelephonyManager.NETWORK_TYPE_EVDO_0,
            TelephonyManager.NETWORK_TYPE_EVDO_A,
            TelephonyManager.NETWORK_TYPE_HSDPA,
            TelephonyManager.NETWORK_TYPE_HSUPA,
            TelephonyManager.NETWORK_TYPE_HSPA,
            TelephonyManager.NETWORK_TYPE_EVDO_B,
            TelephonyManager.NETWORK_TYPE_EHRPD,
            TelephonyManager.NETWORK_TYPE_HSPAP,
            TelephonyManager.NETWORK_TYPE_TD_SCDMA
            -> return "3G"
            TelephonyManager.NETWORK_TYPE_LTE
            -> return "4G"
            TelephonyManager.NETWORK_TYPE_NR
            -> return "5G"
            else -> return "Unknown"
        }

    }

    private fun getWifiInfo(context: Context): String? {
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        val wifiInfo: WifiInfo = wifiManager!!.connectionInfo
        if (wifiInfo.supplicantState == SupplicantState.COMPLETED) {
            return wifiInfo.ssid
        }

        return null
    }

}