/*******************************************************************************
 * Copyright 2024 Amir Hammad (maps@amir.sk)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the “Software”), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
 * THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/

package sk.amir.maps.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sk.amir.maps.ComposeMap
import sk.amir.maps.MapUiSettings
import sk.amir.maps.common.CameraPadding
import sk.amir.maps.common.LatLng
import sk.amir.maps.common.toLatLngBounds
import sk.amir.maps.compose.circle.Circle
import sk.amir.maps.rememberMapState
import sk.amir.maps.updateCamera
import kotlin.math.roundToInt

/**
 * Demonstrates all possible usages of camera
 * - move to a point
 * - move to a point with zoom
 * - zoom camera to fit a polygon on the map with an option to use camera padding
 * - animation/no animation
 */
@Composable
fun CameraDemo() {
    var zoom by rememberSaveable { mutableDoubleStateOf(12.0) }
    var padding by rememberSaveable { mutableDoubleStateOf(15.0) }
    var animateChanges by rememberSaveable { mutableStateOf(false) }
    var latLng by rememberSaveable { mutableStateOf(PointNitra) }

    val mapState = rememberMapState(
        onMapLoad = {
            commandHandle?.updateCamera(
                target = latLng,
                zoom = zoom,
                animated = false,
            )
        },
        onCameraDidChange = {
            latLng = it.target
            zoom = it.zoom
        },
    )

    val zoomToBoth = {
        mapState.commandHandle?.updateCamera(
            bounds = listOf(PointLondon, PointNitra)
                .toLatLngBounds(),
            padding = CameraPadding(padding.dp),
            animated = animateChanges
        )
    }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxHeight()
    ) {
        ComposeMap(
            modifier = Modifier.height(300.dp),
            uiSettings = MapUiSettings(),
            styleUrl = StyleUrls.default,
            mapState = mapState
        ) {
            Circle(PointNitra,
                circleColor = Color.Red, circleRadius = 15.0,
                circleStrokeColor = Color.Black, circleStrokeWidth = 2.0)
            Circle(PointLondon,
                circleColor = Color.Blue, circleRadius = 15.0,
                circleStrokeColor = Color.Black, circleStrokeWidth = 2.0)
        }

        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.Start) {
            Editor(
                name = "Zoom",
                value = zoom,
                onUpdate = {
                    mapState.commandHandle?.updateCamera(
                        target = latLng,
                        zoom = it,
                        animated = animateChanges
                    )
                    // [zoom] variable will be set from onCameraDidChange callback.
                },
                step = 1.0
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = {
                    mapState.commandHandle?.updateCamera(PointNitra, animateChanges)
                }) {
                    Text("Nitra")
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, null)
                }

                TextButton(onClick = {
                    mapState.commandHandle?.updateCamera(PointLondon, animateChanges)
                }) {
                    Text("London")
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, null)
                }
            }

            HorizontalDivider()

            Editor("Padding", padding, { padding = it; zoomToBoth() }, 15.0)

            TextButton(onClick = { zoomToBoth() }) {
                Text("Zoom to both")
                Icon(Icons.AutoMirrored.Filled.ArrowForward, null)
            }

            HorizontalDivider()

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(animateChanges, { animateChanges = it })
                Text("Animate")
            }
        }
    }
}

@Composable
private fun Editor(
    name: String,
    value: Double,
    onUpdate: (Double) -> Unit,
    step: Double,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("$name=${value.roundToInt()}")
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { onUpdate(value - step) }
            ) {
                Text("-")
            }
            Button(
                onClick = { onUpdate(value + step) }
            ) {
                Text("+")
            }
        }
    }
}
