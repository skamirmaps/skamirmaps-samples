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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sk.amir.maps.ComposeMap
import sk.amir.maps.MapUiSettings
import sk.amir.maps.common.CameraPosition
import sk.amir.maps.common.LatLng
import sk.amir.maps.compose.symbol.Symbol
import sk.amir.maps.compose.symbol.Anchor
import sk.amir.maps.rememberMapState
import sk.amir.maps.updateCamera
import kotlin.random.Random

/**
 * This example is about a way to pass the source through local composition to layers defined
 * in the closure.
 */
@Composable
fun SymbolImagesDemo() {
    val mapState = rememberMapState(
        onMapLoad = {
            commandHandle?.updateCamera(
                target = PointNitra,
                zoom = 6.0,
                animated = false,
            )
        },
        onStyleFinishedLoading = {

        },
        onCameraDidChange = { _: CameraPosition ->

        },
    )

    val random = remember { Random(0) }
    val points = remember {
        LatLng.getRandomPoints(
            random = random,
            base = PointNitra,
            span = 5.0,
            count = 20
        )
    }

    var selection by remember { mutableStateOf(false) }
    val images = if (selection) {
        SymbolDemoMapImagesQuestion.entries
    } else {
        SymbolDemoMapImages.entries
    }
    Column(Modifier.fillMaxHeight()) {
        ComposeMap(
            modifier = Modifier.height(300.dp),
            mapState = mapState,
            styleUrl = StyleUrls.default,
            imageContainer = images,
            uiSettings = MapUiSettings()
        ) {
            points.forEachIndexed { index, coordinate ->
                Symbol(
                    center = coordinate,
                    iconName = SymbolDemoMapImagesQuestion.Marker.name,
                    iconOpacity = 0.5,
                    iconScale = 1 + index * 0.1,
                    iconAnchor = Anchor.Bottom,
                )
            }
        }
        Spacer(Modifier.weight(1f))
        Button(onClick = {
            selection = !selection
        }) {
            Text("Next image container")
        }
    }
}
