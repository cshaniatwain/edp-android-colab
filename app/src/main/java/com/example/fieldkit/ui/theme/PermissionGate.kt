package edu.liceo.fieldkit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import edu.liceo.fieldkit.permissions.PermissionStatus
import edu.liceo.fieldkit.permissions.PermissionState
import edu.liceo.fieldkit.permissions.openAppSettings

@Composable
fun PermissionGate(
    state: PermissionState,
    feature: String,
    reason: String,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    when (state.status) {

        PermissionStatus.Granted -> {
            content()
        }

        PermissionStatus.NotAsked -> {
            Button(
                onClick = {
                    state.request()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Allow $feature")
            }
        }

        PermissionStatus.NeedsRationale -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(reason)

                Button(
                    onClick = {
                        state.request()
                    }
                ) {
                    Text("Try again")
                }
            }
        }

        PermissionStatus.Denied -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "$feature is blocked. Turn it on in Settings."
                )

                Button(
                    onClick = {
                        context.openAppSettings()
                    }
                ) {
                    Text("Open Settings")
                }
            }
        }
    }
}
