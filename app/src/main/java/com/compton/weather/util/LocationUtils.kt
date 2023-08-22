package com.compton.weather.util

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

private const val REQUEST_CODE_LOCATION_PERMISSION = 0

fun isLocationPermissionGranted(activity: Activity): Boolean {
    val isCoarseLocationGranted = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    val isFineLocationGranted = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    return if (isCoarseLocationGranted || isFineLocationGranted) {
        true
    } else {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            REQUEST_CODE_LOCATION_PERMISSION
        )
        false
    }
}