package com.whaletail.capecanaveral.connectioncheckbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ConnectionReceiver : BroadcastReceiver(), AnkoLogger {
    lateinit var onChanged: (isConnected: Boolean) -> Unit
    override fun onReceive(context: Context, arg1: Intent) {

        ConnectivityManager.CONNECTIVITY_ACTION
        info { "action: ${arg1.action}" }
        val isConnected = !arg1.getBooleanExtra("noConnectivity", false)
        onChanged(isConnected)
        info { "isConnected: $isConnected" }
        arg1.extras?.keySet()?.forEach { info { "$it ${arg1.extras?.get(it).toString()}" } }
    }
}