package com.whaletail.capecanaveral.boundService

import android.app.Application.getProcessName
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.widget.Toast


class MessengerService : Service() {

    companion object {
        /** Command to the service to display a message  */
        const val MSG_SAY_HELLO = 1
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    private lateinit var mMessenger: Messenger

    /**
     * Handler of incoming messages from clients.
     */
    internal class IncomingHandler(
        context: Context,
        private val applicationContext: Context = context.applicationContext
    ) : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_SAY_HELLO ->
                    Toast.makeText(
                        applicationContext,
                        "hello! ${getProcessName()}",
                        Toast.LENGTH_SHORT
                    ).show()
                else -> super.handleMessage(msg)
            }
        }
    }

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    override fun onBind(intent: Intent): IBinder? {
        mMessenger = Messenger(IncomingHandler(this))
        return mMessenger.binder
    }
}