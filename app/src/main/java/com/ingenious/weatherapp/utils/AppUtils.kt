package com.ingenious.weatherapp.utils

import android.text.TextUtils
import android.util.Patterns


object AppUtils {
    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}