package com.compton.weather.util

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

fun isLocationPermissionGranted(activity: Activity): Boolean {
    val isCoarseLocationGranted = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    val isFineLocationGranted = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    return isCoarseLocationGranted || isFineLocationGranted
}