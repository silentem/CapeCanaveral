package com.whaletail.dynamicfeature

import com.whaletail.capecanaveral.dynamic.DynamicFeature

class DynamicFeatureImpl: DynamicFeature {
    override fun feature(): String {
        return "Hello world"
    }

}