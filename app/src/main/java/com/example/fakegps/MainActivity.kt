package com.example.fakegps

import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFakeLocation(37.4219983, -122.084)
    }

    private fun setFakeLocation(lat: Double, lon: Double) {
        val lm = getSystemService(LOCATION_SERVICE) as LocationManager
        val provider = LocationManager.GPS_PROVIDER

        try {
            lm.addTestProvider(provider, false, false, false, false, true, true, true, 0, 5)
        } catch (_: Exception) { }

        lm.setTestProviderEnabled(provider, true)

        val mockLocation = Location(provider).apply {
            latitude = lat
            longitude = lon
            altitude = 0.0
            time = System.currentTimeMillis()
            accuracy = 1f
        }

        lm.setTestProviderLocation(provider, mockLocation)
    }
}
