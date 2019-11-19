package com.whaletail.capecanaveral.intenttest

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.base.BaseActivity
import kotlinx.android.synthetic.main.activity_intent_test.*


class ChooserBroadcast : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d(javaClass.simpleName, "Boradcast received")
        for (key in p1?.extras?.keySet()!!) {
            Log.d(javaClass.simpleName, " " + p1.extras?.get(key))
        }
    }

}

class IntentTestActivity : BaseActivity() {


    val broadcastReceiver = ChooserBroadcast()

    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter(Intent.ACTION_SEND))
    }

//    override fun onStop() {
//        super.onStop()
//        unregisterReceiver(broadcastReceiver)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_test)
        b_intent.setOnClickListener {


            val receiver = Intent(this, ChooserBroadcast::class.java)
                .putExtra("test", "test")
            val pendingIntent =
                PendingIntent.getBroadcast(this, 0, receiver, PendingIntent.FLAG_UPDATE_CURRENT)

            startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                setType("text/plain")
            }, "Title", pendingIntent.intentSender))
        }
    }
}
