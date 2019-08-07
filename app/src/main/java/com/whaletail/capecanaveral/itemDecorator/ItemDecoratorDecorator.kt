package com.whaletail.capecanaveral.itemDecorator

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.withTranslation
import androidx.core.view.get
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.RecyclerView
import com.whaletail.capecanaveral.R
import java.util.*
import kotlin.collections.LinkedHashMap

class ItemDecoratorDecorator(val context: Context) : RecyclerView.ItemDecoration() {



    private val paddingTop: Int = 10
    private val paddingStart: Int = 10
    private val paddingEnd: Int = 10
    private val paddingBottom: Int = 10
    private val headerHeight: Int = 50.toPx
    private val headerTextSize: Float = 20F
    private val shadow: Shadow = Shadow()
    private var days = LinkedHashMap<Int, TextView>()

    fun setDays(items:List<Int>){
        days.clear()
        days.putAll(
            items
                .mapIndexed { index, item ->
                    index to item
                }.distinctBy { it.second }
                .map {
                    it.first to createHeader(it.second.toString())
                }.toMap()

        )
    }

    /**
     * Create a header layout for the given [dateString].
     */
    private fun createHeader(dateString: String?): TextView {

        val textView = AppCompatTextView(context)
        val params = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            headerHeight
        )
        textView.layoutParams = params
        textView.maxLines = 1
        textView.text = dateString
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, headerTextSize)
        textView.gravity = Gravity.CENTER
        textView.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
        return textView

        //return StaticLayout.Builder.obtain(text,0, text.length, paint, 0).build()
        //return StaticLayout(dateString, paint, width, Layout.Alignment.ALIGN_CENTER, 1f, 0f, false)
    }


    private var mPaint = Paint().apply {
        color = Color.parseColor("#40000000")
        isAntiAlias = true
    }

//    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//        /*if (lastListSize!=currentList?.size){
//            fillDays()
//        }*/
//        if (days.isEmpty() || parent.isEmpty()) return
//        val adapterPosition = parent.getChildAdapterPosition(view)
//        /*currentList?.get(adapterPosition)?.let {
//            val trDate = toDateString(it.transactionDate)
//            if (trDate!=null && !days1.containsKey(trDate)){
//                days1[trDate] = createHeader(trDate)
//                outRect.top = headerHeight
//            }
//        }*/
//
//        days[adapterPosition]?.let {
//            //measureHeaderView(it, parent)
//            outRect.top = headerHeight
//        }
//    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        var earliestFoundHeaderPos = -1
        var prevHeaderTop = Int.MAX_VALUE
        var shadowPosition: Int = -1

        for (i in parent.childCount - 1 downTo 0) {
            val view = parent.getChildAt(i) ?: continue
            val viewTop = view.top + view.translationY.toInt()

            if (view.bottom > 0 && viewTop < parent.height) {
                val position = parent.getChildAdapterPosition(view)
                days[position]?.let { layout ->
                    Log.d("TAG", "onDrawOver first: '${layout.text}' view top: $viewTop, layout height: ${layout.height}")
                    measureHeaderView(layout, parent)
                    val top = (viewTop - paddingTop - headerHeight)
//                        .coerceAtLeast(paddingTop)
//                        .coerceAtMost(prevHeaderTop - headerHeight)
                    c.save()

                    Log.d("TAG", "onDrawOver: called for position $i")
                    c.withTranslation(y = top.toFloat()) {
                        layout.draw(c)
                        if (shadowPosition == -1 && (viewTop < headerHeight || (position > 0 && viewTop < headerHeight * 2))) {
                            shadow.onDraw(layout, c)
                            shadowPosition = position
                        }
                    }

                    c.restore()
                    earliestFoundHeaderPos = position
                    prevHeaderTop = viewTop - headerHeight
                }
            }
        }
        // If no headers found, ensure header of the first shown item is drawn.
        if (earliestFoundHeaderPos < 0) {
            earliestFoundHeaderPos = parent.getChildAdapterPosition(parent[0]) + 1
        }

        // Look back over headers to see if a prior item should be drawn sticky.
        for (headerPos in days.keys.reversed()) {
            if (headerPos < earliestFoundHeaderPos) {
                days[headerPos]?.let {
                    measureHeaderView(it, parent)

                    val top = (prevHeaderTop - headerHeight).coerceAtMost(paddingTop)
                    Log.d("TAG", "onDrawOver sticky: '${it.text}' top: $top, layout height: ${it.height}")

                    c.withTranslation(y = top.toFloat()) {
                        it.draw(c)
                        if (shadowPosition == -1 && top >= 0) {
                            shadow.onDraw(it, c)
                        }
                    }
                }
                break
            }
        }
        c.drawPaint(mPaint)
        Log.d("TAG", "________________________________________________________")
    }


    private fun measureHeaderView(view: View, parent: ViewGroup) {

        val displayMetrics = parent.context.resources.displayMetrics

        val widthSpec = View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, View.MeasureSpec.UNSPECIFIED)

        val childWidth = ViewGroup.getChildMeasureSpec(
            widthSpec,
            parent.paddingLeft + parent.paddingRight, view.layoutParams.width
        )
        val childHeight = ViewGroup.getChildMeasureSpec(
            heightSpec,
            parent.paddingTop + parent.paddingBottom, view.layoutParams.height
        )

        view.measure(childWidth, childHeight)

        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
    }


    val Int.toDp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()
    val Int.toPx: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()


}