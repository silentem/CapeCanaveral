package com.whaletail.capecanaveral.itemDecorator

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.GradientDrawable.RADIAL_GRADIENT
import android.graphics.drawable.GradientDrawable.Orientation.*
import android.view.View


/**
 * Project SportBankApp
 * Created by Serhiy Plekhov on 22.03.19.
 */
class Shadow {
    private val startColor = Color.parseColor("#40000000")
    private val endColor = Color.parseColor("#00000000")
    private val shadowLength = (2 * Resources.getSystem().displayMetrics.density).toInt()

    private val linearGradient: GradientDrawable
    private val radialGradient: GradientDrawable
    private var colors: IntArray? = null
    init {
        colors = intArrayOf(startColor, endColor)
        linearGradient = GradientDrawable(TOP_BOTTOM, colors)
        radialGradient = GradientDrawable()
        radialGradient.gradientType = RADIAL_GRADIENT
        radialGradient.colors = colors
        radialGradient.gradientRadius = shadowLength.toFloat()

    }

    fun onDraw(view: View, canvas: Canvas) {
        val height = view.height
        val width = view.width

        linearGradient.setBounds(shadowLength, height, width, height + shadowLength)
        linearGradient.orientation = TOP_BOTTOM
        linearGradient.draw(canvas)

        linearGradient.setBounds(width, shadowLength, width + shadowLength, height)
        linearGradient.orientation = LEFT_RIGHT
        linearGradient.draw(canvas)

        radialGradient.setBounds(0, height, shadowLength, height + shadowLength)
        radialGradient.setGradientCenter(1f, 0f)
        radialGradient.draw(canvas)

        radialGradient.setBounds(width, height, width + shadowLength, height + shadowLength)
        radialGradient.setGradientCenter(0f, 0f)
        radialGradient.draw(canvas)

        radialGradient.setBounds(width, 0, width + shadowLength, shadowLength)
        radialGradient.setGradientCenter(0f, 1f)
        radialGradient.draw(canvas)
    }
}