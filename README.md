# skamirmaps - compose multiplatform wrapper to work with maps
**Library skamirmaps ** (the library) builds on top of MapLibre Native libraries. One for iOS and one for Android. Using kotlin interop it was possible to link toward the native libraries without modifying their source code.  
The use of external dependencies is being kept at minimum.  
[**This Samples repository**](https://github.com/todo) serves as a public documentation of capabilities of the library.

#### ! **This project has a dependency on skamirmaps (The library)**
To get access to the library itself (free of charge) just fill out the following questionaire and further instructions will be provided. 

_**[Request access ->](https://forms.gle/QfNoBa4dh2aTLC1N8)**_

# Requirements
- Apple computer to run thise sample app on your Apple device.
- Knowledge of [Compose](https://developer.android.com/compose) is necessary
- It helps if you have basic understanding of how MapLibre works and that you have to use a tile server to show tiles on the map.  
  This samples repository uses [MapTiler](https://www.maptiler.com) as a tile provider. You have to request an API key from MapTiler. Please remove `_template` suffix from the following filename `skamirmaps-samples/src/commonMain/kotlin/sk/amir/maps/samples/MapApiKey.kt_template` and fill in your own MapTilerApi key in there.

  If you prefer another tile provider, just modify the style urls in (`skamirmaps-samples/src/commonMain/kotlin/sk/amir/maps/samples/Configuration.kt`).

# Features
Many features have been developed. For some of them I have created an article on my blog.
- **Features**:
  - [Circle](https://amir.sk/32/compose-map-intro/)
  - Symbol(icon + text)
  - Line
  - Fill
- style switching - possible dark mode
- [Camera control](https://amir.sk/184/composemap-camera-control/)
- Controlling map settings
- Features are ordered bysed on order of composable calls. No z-indices are necessary anymore! (yaay)

# Acknowledgement
The library builds on top of [MapLibre Native](https://maplibre.org). And most of the great hard work has been done by MapLibre Native maintainers. 
However this project is NOT affiliated with MapLibre Native project.
And also big thanks goes to developers of [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) and [Compose Runtime](https://developer.android.com/jetpack/androidx/releases/compose-runtime) itself. 

# Developer
My name is **Amir Hammad**, I'm based in Amsterdam, Netherlands and I'm the creator and currently the sole maintainer of the library and of this repository.
You can reach me at [maps@amir.sk](mailto:maps@amir.sk) or at [Linked in](https://www.linkedin.com/in/amir-sk/)

# License
This sample repository is licensed under [MIT license](https://opensource.org/license/mit). See LICENSE file for more info.
