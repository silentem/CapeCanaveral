package com.whaletail.capecanaveral.contrast

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.setContrast
import kotlinx.android.synthetic.main.activity_contrast.*
import java.util.*

class ContrastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrast)

        var contrast = 0f
        var brightness = 0f

        sb_seekbar_brightness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                brightness = p1.toFloat()/10
                iv_contrast.setContrast(contrast, brightness)

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        sb_seekbar_contrast.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                contrast = p1.toFloat()
                iv_contrast.setContrast(contrast, brightness)

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

    }
}
