package com.whaletail.capecanaveral.activityMainfestTags.allowEmbeded

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.whaletail.capecanaveral.R
import kotlinx.android.synthetic.main.activity_allow_embed.*


class AllowEmbedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allow_embed)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, BubbleAllowEmbededActivity::class.java))
        }
    }

}
