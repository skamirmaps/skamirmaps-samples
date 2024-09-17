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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import sk.amir.maps.ComposeMap
import sk.amir.maps.MapUiSettings
import sk.amir.maps.common.LatLng
import sk.amir.maps.compose.circle.Circle
import sk.amir.maps.rememberMapState
import sk.amir.maps.updateCamera

@Composable
internal fun PointsDemo() {
    val scope = rememberCoroutineScope()
    val sliderState = remember { SliderState(0.5f) }
    Column(
        modifier = Modifier.padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.TopCenter,
        ) {
            var location2 by rememberSaveable { mutableStateOf(PointNitra2) }

            val mapState = rememberMapState(
                onMapLoad = {
                    scope.launch {
                        commandHandle?.updateCamera(
                            target = PointNitra,
                            zoom = 13.0,
                            animated = false,
                        )
                    }
                },
            )

            ComposeMap(
                modifier = Modifier.fillMaxSize(),
                uiSettings = MapUiSettings(),
                styleUrl = StyleUrls.default,
                mapState = mapState
            ) {
                Circle(
                    center = PointNitra,
                    circleRadius = sliderState.value * 10.0,
                    circleColor = createColorForSliderValue(sliderState.value),
                )
                Circle(
                    center = location2,
                    circleRadius = ((1 - sliderState.value) * 10.0),
                    circleColor = createColorForSliderValue(1 - sliderState.value),
                )
            }
        }
        Slider(sliderState, Modifier.padding(horizontal = 16.dp))
    }
}

/**
 * Transition between green and blue
 */
private fun createColorForSliderValue(value: Float): Color {
    return Color((value * 255).toInt() + ((1 - value) * 255).toInt() * 256)
}
