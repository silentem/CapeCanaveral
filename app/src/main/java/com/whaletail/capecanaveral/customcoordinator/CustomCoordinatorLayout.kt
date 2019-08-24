package com.whaletail.capecanaveral.customcoordinator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class CustomCoordinatorLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CoordinatorLayout(context, attrs, defStyleAttr), AnkoLogger {


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        super.onLayout(changed, l, t, r, b)
        info { "onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) called" }
        val behavior = BottomSheetBehavior.from(findViewWithTag<View>("bottomSheet"))
        val centerView = findViewWithTag<View>("centerView")
        val card = findViewWithTag<View>("card")
        behavior.peekHeight = centerView.top - (centerView.top - card.measuredHeight - card.top)
        info { "centerView.top: ${centerView.top}" }
        info { "card.top: ${card.top}" }


    }

}