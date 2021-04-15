package com.digging.coroutine.extensions

import android.view.View

fun View.click(block: (v: View) -> Unit) {
    setOnClickListener {
        block(it)
    }
}