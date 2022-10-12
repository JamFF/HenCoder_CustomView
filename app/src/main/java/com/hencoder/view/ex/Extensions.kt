package com.hencoder.view.ex

import android.content.res.Resources
import android.util.TypedValue

/**
 * Description:
 * Author: 傅健
 * CreateDate: 2022/10/12 11:37
 */

val Float.dp2px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )