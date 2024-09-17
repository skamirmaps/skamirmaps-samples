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

import sk.amir.maps.common.LatLng
import kotlin.random.Random

internal val PointNitra: LatLng = LatLng(48.3178495,18.0899431)
internal val PointNitra2: LatLng = LatLng(48.30978495,18.0799431)
internal val PointLondon: LatLng = LatLng(51.506786631771675, -0.12728422025646574)
internal val PolygonNitra: List<LatLng> = listOf(
    17.92658996792636 to 48.425811829379455,
    17.808446506151398 to 48.27073402436582,
    18.12429404535692 to 48.18656851455367,
    18.22864452262374 to 48.26887046410724,
    18.228607409577222 to 48.40563686243894,
    18.135281260742033 to 48.356408610252004,
    17.99248574332438 to 48.351060557181654,
    18.09134010194515 to 48.46228668269859,
    17.92658996792636 to 48.425811829379455,
)
    .map { LatLng(it.second, it.first) }
internal fun LatLng.Companion.getRandomPoints(
    random: Random,
    base: LatLng,
    span: Double,
    count: Int,
): List<LatLng> {
    return mutableListOf<LatLng>()
        .apply {
            repeat(count) {
                add(
                    LatLng(
                        latitude = base.latitude + random.nextDouble(-span, span),
                        longitude = base.longitude + random.nextDouble(-span, span)
                    )
                )
            }
        }
        .toList()
}

internal object StyleUrls {
    const val default = "https://api.maptiler.com/maps/streets-v2/style.json?key=$MapApiKey"
    const val dark = "https://api.maptiler.com/maps/backdrop/style.json?key=$MapApiKey"
}
