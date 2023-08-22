package com.compton.weather.data.local

data class LocationData(
    var city: String?,
    var latitude: Double?,
    var longitude: Double?
) {
    constructor(city: String?) : this(city, null, null)
}