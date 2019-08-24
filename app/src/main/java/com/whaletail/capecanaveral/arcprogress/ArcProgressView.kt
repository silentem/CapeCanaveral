package com.whaletail.capecanaveral.arcprogress

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.whaletail.capecanaveral.toPx
import org.jetbrains.anko.AnkoLogger
import kotlin.math.min
import kotlin.math.round


class ArcProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), AnkoLogger {

    val strokeSize = 10.toPx.toFloat()

    val initPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = strokeSize
        strokeCap = Paint.Cap.ROUND
        color = Color.parseColor("#27addd")
        isAntiAlias = true
    }
    val defaultPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.parseColor("#e4e9f1")
        strokeWidth = strokeSize
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }
    val continuePaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.parseColor("#a95edc")
        strokeWidth = strokeSize
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }
    val rect = RectF()


    val changeColorProgress = 100f

    val maxProgress = 555F

    val blankSpace = 60F

    val maxAngle = 360 - blankSpace

    val initPath = Path()

    val continuePath = Path()

    val defaultPath = Path()

    var progress: Float = 0F
        set(value) {

            val start = field

            field = if (value <= maxProgress) value else maxProgress

            val end = field

            val animator = ValueAnimator()

            animator.setFloatValues(start, end)

            animator.duration = 200
            animator.interpolator = AccelerateDecelerateInterpolator()

            animator.addUpdateListener {

                val progress = it.animatedValue as Float

                initPath.addArc(
                    rect,
                    getStartAngle(),
                    getAngleFromProgress(if (progress <= changeColorProgress) progress else changeColorProgress)
                )
                continuePath.addArc(
                    rect,
                    getStartAngle(),
                    getAngleFromProgress(progress)
                )
                invalidate()
            }

            animator.start()
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val min = min(widthSize, heightSize)
        rect.set(strokeSize, strokeSize, min.toFloat() - strokeSize, min.toFloat() - strokeSize)
        defaultPath.addArc(rect, getStartAngle(), getAngleFromProgress(maxProgress))
        setMeasuredDimension(min, min)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            save()
            rotate(90f, measuredWidth / 2F, measuredWidth / 2F)
            drawPath(defaultPath, defaultPaint)
            drawPath(continuePath, continuePaint)
            drawPath(initPath, initPaint)
            restore()
        }
    }

    private fun getAngleFromProgress(progress: Float) =
        round(maxAngle * progress / maxProgress)

    private fun getStartAngle() = round(blankSpace / 2)

}