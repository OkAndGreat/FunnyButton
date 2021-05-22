package com.example.funnybutton

import android.content.Context
import android.widget.Toast

object ToastUtil {
    fun String.show(context: Context?, time: Int =Toast.LENGTH_SHORT) {
        Toast.makeText(context, this, time).show()
    }
}
