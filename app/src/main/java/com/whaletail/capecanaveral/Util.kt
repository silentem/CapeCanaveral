package com.whaletail.capecanaveral

import android.content.res.Resources


val Int.toDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()


fun log(v: Any) = println("[${Thread.currentThread().name}] $v")

fun Float.format(digits: Int): String = java.lang.String.format("%.${digits}f", this)