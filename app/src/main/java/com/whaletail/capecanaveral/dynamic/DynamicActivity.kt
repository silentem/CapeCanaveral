package com.whaletail.capecanaveral.dynamic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.whaletail.capecanaveral.R
import org.jetbrains.anko.toast
import java.util.*

class DynamicActivity : AppCompatActivity() {


    lateinit var dynamicFeature: DynamicFeature

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic)


        dynamicFeature = ServiceLoader.load(DynamicFeature::class.java, DynamicFeature::class.java.classLoader).iterator().next()

        toast(dynamicFeature.feature())

    }
}
