package com.whaletail.capecanaveral.activityMainfestTags.parentactivityname

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.whaletail.capecanaveral.R

import kotlinx.android.synthetic.main.activity_parent_name.*

class ParentActivityNameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_name)

        toolbar.setNavigationOnClickListener { onBackPressed() }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
