package com.whaletail.capecanaveral.arcprogress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.whaletail.capecanaveral.R
import kotlinx.android.synthetic.main.activity_arc_progress.*
import org.jetbrains.anko.toast

class ArcProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arc_progress)

        b_add_progress.setOnClickListener {
            arc.progress += 55.5F
        }

    }
}
