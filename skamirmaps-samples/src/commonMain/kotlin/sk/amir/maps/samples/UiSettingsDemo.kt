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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sk.amir.maps.ComposeMap
import sk.amir.maps.MapUiSettings
import sk.amir.maps.common.LatLng
import sk.amir.maps.rememberMapState
import sk.amir.maps.updateCamera

@Composable
fun UiSettingsDemo() {
    val state = rememberMapState(
        onMapLoad = {
            commandHandle?.updateCamera(
                target = PointNitra,
                zoom = 14.0,
                animated = false
            )
        },
    )

    val settings = remember { MapUiSettings() }

    Column(
        Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        ComposeMap(
            modifier = Modifier.height(300.dp),
            styleUrl = StyleUrls.default,
            mapState = state,
            uiSettings = settings
        ) {}

        CheckboxWithTitle("isAttributionEnabled", settings.isAttributionEnabled) {
            settings.isAttributionEnabled = it
        }
        CheckboxWithTitle("isLogoEnabled", settings.isLogoEnabled) {
            settings.isLogoEnabled = it
        }
        CheckboxWithTitle("isCompassEnabled", settings.isCompassEnabled) {
            settings.isCompassEnabled = it
        }
        CheckboxWithTitle("isRotateGesturesEnabled", settings.isRotateGesturesEnabled) {
            settings.isRotateGesturesEnabled = it
        }
        CheckboxWithTitle("isTiltGesturesEnabled", settings.isTiltGesturesEnabled) {
            settings.isTiltGesturesEnabled = it
        }
        CheckboxWithTitle("isZoomGesturesEnabled", settings.isZoomGesturesEnabled) {
            settings.isZoomGesturesEnabled = it
        }
        CheckboxWithTitle("isScrollGesturesEnabled", settings.isScrollGesturesEnabled) {
            settings.isScrollGesturesEnabled = it
        }
    }
}

@Composable
private fun CheckboxWithTitle(name: String, checked: Boolean, onCheckedChanged: (newValue: Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked, onCheckedChange = onCheckedChanged)
        Text(name, Modifier.weight(1f))
    }
}