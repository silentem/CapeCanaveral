package com.whaletail.capecanaveral.spananimate

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.Layout
import android.text.style.LeadingMarginSpan
import android.view.View
import com.whaletail.capecanaveral.toPx

class AnimatingSpan(val view: View) : LeadingMarginSpan {

    val rect = RectF(0F, 0F, 100.toPx.toFloat(), 10.toPx.toFloat())

    val paint = Paint().apply {
        color = Color.RED
    }

    val va = ValueAnimator.ofInt(0, 50).apply {
        duration = 5000
        addUpdateListener {
            rect.top += it.animatedValue as Int
            rect.bottom += it.animatedValue as Int
            view.invalidate()
        }
    }

    fun startAnimation() {
        va.start()
    }

    override fun drawLeadingMargin(
        p0: Canvas?,
        p1: Paint?,
        p2: Int,
        p3: Int,
        p4: Int,
        p5: Int,
        p6: Int,
        p7: CharSequence?,
        p8: Int,
        p9: Int,
        p10: Boolean,
        p11: Layout?
    ) {


        p0?.drawRect(rect, paint)

    }

    override fun getLeadingMargin(p0: Boolean): Int = 0
}