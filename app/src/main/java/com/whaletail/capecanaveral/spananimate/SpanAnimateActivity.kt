package com.whaletail.capecanaveral.spananimate

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import androidx.appcompat.app.AppCompatActivity
import com.whaletail.capecanaveral.R
import kotlinx.android.synthetic.main.activity_span_animate.*

class SpanAnimateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_span_animate)



    }

    override fun onResume() {
        super.onResume()
        val s = "asdasd"
        val animatingSpan = AnimatingSpan(tv_span)
        tv_span.text = SpannableString(s).apply {
            setSpan(animatingSpan, 0, s.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }

        animatingSpan.startAnimation()
    }
}
