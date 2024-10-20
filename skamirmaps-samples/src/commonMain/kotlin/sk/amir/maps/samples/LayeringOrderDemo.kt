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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import sk.amir.maps.ComposeMap
import sk.amir.maps.MapUiSettings
import sk.amir.maps.common.LatLng
import sk.amir.maps.compose.fill.Fill
import sk.amir.maps.rememberMapState
import sk.amir.maps.updateCamera

@Composable
fun LayeringOrderDemo() {
    val state = rememberMapState(
        onMapLoad = {
            commandHandle?.updateCamera(
                target = PointLondon,
                zoom = 6.0,
                animated = false,
            )
        }
    )

    var order by remember { mutableStateOf(true) }

    val displacement = 0.25
    val green = @Composable { FillWithOffset(0.0, 0.0, Color.Green) }
    val blue = @Composable { FillWithOffset(-displacement/2, displacement, Color.Blue) }
    val red = @Composable { FillWithOffset(displacement/2, displacement, Color.Red) }

    Column {
        Box(Modifier.weight(1f).fillMaxWidth()) {
            ComposeMap(
                modifier = Modifier.fillMaxSize(),
                styleUrl = StyleUrls.default,
                uiSettings = remember { MapUiSettings() },
                mapState = state,
                imageContainer = listOf(),
            ) {
                if (order) {
                    blue()
                    red()
                    green()
                } else {
                    green()
                    red()
                    blue()
                }
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(if (order) "blue, red, green" else "green, red, blue")
            Button(
                onClick = { order = !order },
            ) {
                Text("Reorder")
            }
        }
    }
}

@Composable
private fun FillWithOffset(
    offsetX: Double,
    offsetY: Double,
    color: Color
) {
    val baseCoord = PointLondon.let {
        LatLng(
            latitude = it.latitude + offsetY,
            longitude = it.longitude + offsetX
        )
    }
    Fill(
        points = listOf(
            baseCoord,
            LatLng(baseCoord.latitude + 0.5, baseCoord.longitude),
            LatLng(baseCoord.latitude + 0.5, baseCoord.longitude + 0.5),
            LatLng(baseCoord.latitude, baseCoord.longitude + 0.5),
        ),
        color = color,
        opacity = 0.95,
    )
}
