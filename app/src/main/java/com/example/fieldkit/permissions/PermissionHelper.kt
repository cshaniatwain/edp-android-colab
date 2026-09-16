package edu.liceo.fieldkit.permissions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LifecycleResumeEffect

enum class PermissionStatus {
    Granted,
    NotAsked,
    NeedsRationale,
    Denied
}

data class PermissionState(
    val status: PermissionStatus,
    val request: () -> Unit
)

fun Activity.permStatus(
    permission: String,
    asked: Boolean
): PermissionStatus {

    if (
        ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        return PermissionStatus.Granted
    }

    if (
        ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            permission
        )
    ) {
        return PermissionStatus.NeedsRationale
    }

    return if (asked) {
        PermissionStatus.Denied
    } else {
        PermissionStatus.NotAsked
    }
}

@Composable
fun rememberPermission(
    permission: String
): PermissionState {

    val activity = LocalActivity.current

    var asked by rememberSaveable {
        mutableStateOf(false)
    }

    var status by remember {
        mutableStateOf(
            activity?.permStatus(
                permission = permission,
                asked = asked
            ) ?: PermissionStatus.NotAsked
        )
    }

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->

            asked = true

            status = activity?.permStatus(
                permission = permission,
                asked = true
            ) ?: PermissionStatus.NotAsked
        }

    LifecycleResumeEffect(permission) {

        status = activity?.permStatus(
            permission = permission,
            asked = asked
        ) ?: PermissionStatus.NotAsked

        onPauseOrDispose {
        }
    }

    return PermissionState(
        status = status,
        request = {
            launcher.launch(permission)
        }
    )
}

fun Context.openAppSettings() {

    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.parse("package:$packageName")
    )

    startActivity(intent)
}
