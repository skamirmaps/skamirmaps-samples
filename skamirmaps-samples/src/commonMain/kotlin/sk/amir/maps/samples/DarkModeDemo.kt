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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sk.amir.maps.ComposeMap
import sk.amir.maps.MapUiSettings
import sk.amir.maps.common.LatLng
import sk.amir.maps.compose.circle.Circle
import sk.amir.maps.rememberMapState
import sk.amir.maps.updateCamera
import kotlin.random.Random

/**
 * SymbolLayer and CircleLayer
 * Demonstrates:
 *  - when the style is reloaded, the layer and sources are added again.
 */
@Composable
fun DarkModeDemo() {
    val mapState = rememberMapState(
        onMapLoad = {
            commandHandle?.updateCamera(
                target = PointNitra,
                zoom = 11.0,
                animated = false
            )
        }
    )
    var toggleSwitch by rememberSaveable { mutableStateOf(true) }

    val styleUrl = if (toggleSwitch) {
        StyleUrls.dark
    } else {
        StyleUrls.default
    }

    val points = remember {
        LatLng.getRandomPoints(Random(0), PointNitra, 0.03, 20)
    }

    Column(Modifier.fillMaxWidth()) {
        ComposeMap(
            modifier = Modifier.height(300.dp),
            uiSettings = MapUiSettings(),
            styleUrl = styleUrl,
            imageContainer = SymbolDemoMapImages.entries,
            mapState = mapState
        ) {
            points.forEach {
                Circle(
                    center = it,
                    circleRadius = 5.0,
                    circleColor = Color.Blue,
                )
            }
        }

        Spacer(Modifier.weight(1f))
        Button(
            onClick = { toggleSwitch = !toggleSwitch }
        ) {
            Text("Next style")
        }
    }
}
