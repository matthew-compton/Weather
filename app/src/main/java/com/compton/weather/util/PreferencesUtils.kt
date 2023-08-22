package com.compton.weather.util

import android.app.Activity
import android.content.Context

private const val KEY_LAST_SEARCH = "KEY_LAST_SEARCH"

fun getCachedLocation(activity: Activity): String {
    val preferences = activity.getPreferences(Context.MODE_PRIVATE) ?: return ""
    return preferences.getString(KEY_LAST_SEARCH, "") ?: ""
}

fun setCachedLocation(activity: Activity, search: String) {
    val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
    with(sharedPref.edit()) {
        putString(KEY_LAST_SEARCH, search)
        apply()
    }
}
