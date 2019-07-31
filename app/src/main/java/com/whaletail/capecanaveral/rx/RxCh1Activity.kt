package com.whaletail.capecanaveral.rx

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.print.PrintHelper
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.base.BaseActivity
import kotlinx.android.synthetic.main.activity_rx_ch1.*
import org.jetbrains.anko.info


class RxCh1Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_ch1)

        b_asdf.setOnClickListener {
            val bitmapFromView = getBitmapFromView(ll_asdf)
            iv_asdf.setImageBitmap(bitmapFromView)
            doPhotoPrint(bitmapFromView)
        }


        if (Intent.ACTION_VIEW == intent.action) {
            val uri = intent.data
            info { uri.toString() }
        }

    }

    private fun doPhotoPrint(bitmap: Bitmap) {
        this.also { context ->
            PrintHelper(context).apply {
                scaleMode = PrintHelper.SCALE_MODE_FIT
            }.also { printHelper ->
                printHelper.printBitmap("droids.jpg - test print", bitmap)
            }
        }
    }

    fun getBitmapFromView(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.getBackground()
        if (bgDrawable != null)
            bgDrawable!!.draw(canvas)
        else
            canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }
}
