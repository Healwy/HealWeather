package com.xuniyishifanchen.healweather.ui.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isGone")
fun bindIsInvisible(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.INVISIBLE
    } else {
        View.VISIBLE
    }
}