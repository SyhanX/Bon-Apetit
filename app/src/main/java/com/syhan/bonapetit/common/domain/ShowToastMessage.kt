package com.syhan.bonapetit.common.domain

import android.content.Context
import android.widget.Toast

fun showToastMessage(context: Context, text: String = "Copied to clipboard") {
    val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
    toast.show()
}