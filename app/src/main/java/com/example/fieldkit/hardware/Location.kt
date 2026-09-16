package edu.liceo.fieldkit.hardware

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

@SuppressLint("MissingPermission")
fun Context.currentLocation(onResult: (Location?) -> Unit) {
    val client = LocationServices.getFusedLocationProviderClient(this)
    val token = CancellationTokenSource().token
    client.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, token)
        .addOnSuccessListener { location ->
            onResult(location)
        }
        .addOnFailureListener {
            onResult(null)
        }
}
