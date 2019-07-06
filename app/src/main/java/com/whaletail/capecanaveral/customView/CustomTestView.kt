package com.whaletail.capecanaveral.customView

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class CustomTestView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), AnkoLogger {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        info { "widthMeasureSpec: $widthSpecMode , $widthSpecSize, heightMeasureSpec: $heightSpecMode , $heightSpecSize" }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        info { "changed: $changed, left: $left, top: $top, right: $right, bottom: $bottom" }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}