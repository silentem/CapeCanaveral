package com.whaletail.capecanaveral.activityMainfestTags.configchanges

import android.content.res.Configuration
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.base.BaseActivity

import kotlinx.android.synthetic.main.activity_config_changes.*
import org.jetbrains.anko.info

class ConfigChangesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_changes)
        setSupportActionBar(toolbar)

    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {snack("ORIENTATION_LANDSCAPE")}
            Configuration.ORIENTATION_PORTRAIT -> {snack("ORIENTATION_PORTRAIT")}
        }

        info { "Locale tags: ${newConfig.locale.language}" }
        info { "Keyboard: ${newConfig.keyboardHidden}" }
    }


    fun snack(message: String) {
        Snackbar.make(fab, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

}
