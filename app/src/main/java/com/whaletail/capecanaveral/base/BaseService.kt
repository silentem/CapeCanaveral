package com.whaletail.capecanaveral.base

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import org.jetbrains.anko.AnkoLogger

@SuppressLint("Registered")
abstract class BaseService: Service(), AnkoLogger {
}