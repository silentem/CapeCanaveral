package com.whaletail.capecanaveral

import android.content.res.Resources
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.widget.ImageView


val Int.toDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()


fun log(v: Any) = println("[${Thread.currentThread().name}] $v")

fun Float.format(digits: Int): String = java.lang.String.format("%.${digits}f", this)

// SeekBar ranges from 0 to 90
// contrast ranges from 1 to 10
fun ImageView.setContrast(contrast: Float, brightness: Float) {
    colorFilter = ColorMatrixColorFilter(
        ColorMatrix(
            floatArrayOf(
                contrast, 0f, 0f, 0f, brightness,
                0f, contrast, 0f, 0f, brightness,
                0f, 0f, contrast, 0f, brightness,
                0f, 0f, 0f, 1f, 0f
            )
        )
    )
}