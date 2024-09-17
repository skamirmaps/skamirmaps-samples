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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

internal enum class DemoType(val factory: @Composable () -> Unit) {
    Points({ PointsDemo() }),
    Polygon({ PolygonDemo() }),
    DarkMode({ DarkModeDemo() }),
    CameraDemo({ CameraDemo() }),
    UiSettingsDemo({ UiSettingsDemo() }),
    SymbolImagesDemo({ SymbolImagesDemo() }),
    ReloadDemo({ ReloadDemo() }),
    ;

    @Composable
    fun content() {
        factory()
    }
}

@Composable
fun DemoScreen() {
    var demoType by rememberSaveable { mutableStateOf<DemoType?>(null) }
    var expanded by rememberSaveable { mutableStateOf(true) }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End,
        ) {
            Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                demoType?.content()
            }
            Button(
                onClick = { expanded = true }
            ) {
                Icon(Icons.Default.Menu, "menu")
            }
        }

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(16.dp).align(Alignment.End),
            ) {
                DemoType.entries
                    .map {
                        OutlinedButton(onClick = {
                            demoType = it
                            expanded = false
                        }) {
                            Text(it.name)
                        }
                    }
            }
        }
    }
}
