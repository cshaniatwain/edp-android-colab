package edu.liceo.fieldkit.ui

import android.Manifest
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import edu.liceo.fieldkit.hardware.CameraPreview
import edu.liceo.fieldkit.hardware.buzz
import edu.liceo.fieldkit.hardware.isShake
import edu.liceo.fieldkit.hardware.loadThumb
import edu.liceo.fieldkit.hardware.rememberAccelerometer
import edu.liceo.fieldkit.hardware.takePhoto
import edu.liceo.fieldkit.permissions.rememberPermission
import java.io.File

@Composable
fun CameraCard() {

    val context = LocalContext.current

    val camera = rememberPermission(
        Manifest.permission.CAMERA
    )

    val capture = remember {
        ImageCapture.Builder().build()
    }

    var photo by remember {
        mutableStateOf<File?>(null)
    }

    val shake = rememberAccelerometer()
    var lastShot by remember { mutableLongStateOf(0L) }

    LaunchedEffect(shake) {
        val now = System.currentTimeMillis()
        if (isShake(shake) && (now - lastShot > 1500)) {
            lastShot = now
            takePhoto(
                context = context,
                capture = capture
            ) { saved ->
                photo = saved
            }
            context.buzz()
            Log.d("FieldKit", "Shake capture")
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "Field photo",
                style = MaterialTheme.typography.titleMedium
            )

            PermissionGate(
                state = camera,
                feature = "Camera",
                reason = "We need the camera to photograph the issue you report."
            ) {

                CameraPreview(
                    capture = capture,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )

                Button(
                    onClick = {
                        takePhoto(
                            context = context,
                            capture = capture
                        ) { saved ->
                            photo = saved
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Take photo")
                }

                photo?.let {
                    Text(
                        text = "Saved: ${it.name}"
                    )
                }
            }

            photo?.let { file ->

                val thumb = remember(file) {
                    loadThumb(file)
                }

                thumb?.let {
                    Image(
                        bitmap = it,
                        contentDescription = "Last photo",
                        modifier = Modifier.size(96.dp)
                    )
                }
            }
        }
    }
}
