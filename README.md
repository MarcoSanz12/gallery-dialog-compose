# GalleryDialogCompose
[![Jitpack link](https://jitpack.io/v/MarcoSanz12/gallery-dialog-compose.svg)](https://jitpack.io/#MarcoSanz12/gallery-dialog-compose)

GalleryDialogCompose is a library created to display **images** and **360 panoramic images** in a comfortable and simple way. It allows adding explanatory text for the photo in a footer that appears and hides dynamically.

Standard images use the [Zoomable](https://github.com/usuiat/Zoomable) library, which allows zooming through gestures.

360 images use the [PanoramaGL](https://github.com/hannesa2/panoramaGL) library, which allows zooming through gestures and activating sensor rotation.

## Demonstration
**GalleryDialog**

![GalleryDialog demo](https://github.com/user-attachments/assets/77534427-a148-4b09-937f-e17aca3c2ce3)

**PanoramaDialog**

![PanoramaDialog demo](https://github.com/user-attachments/assets/7908b97f-cf17-4430-a350-a5a7f02d06c9)

*(Both galleries support screen rotation using the rotation button)*

## How to use
Just invoke the Dialog Composables

```kotlin
if (selectedItem != null)
  GalleryDialog(
    items = galleryItems,
    initialItem = galleryItems.indexOf(selectedItem),
    options = rememberGalleryDialogOptions(),
    onDismissRequest = onDismissRequest
  )
```
```kotlin
if (selectedItem != null)
  PanoramaDialog(
    item = selectedItem,
    options = rememberPanoramaDialogOptions(),
    onDismissRequest = onDismissRequest
  )
```
You can customize some minor settings _(buttons visibility, allowing rotation, downloading, sharing...)_ using `rememberGalleryDialogOptions()` and `rememberPanoramaDialogOptions()`

Check the sample app to see all these features implemented!

# Installation
Add the Jitpack repository to your build.gradle file
```gradle
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
 ```
Add the dependency in your module
```gradle
dependencies {
	        implementation 'com.github.MarcoSanz12:gallery-dialog-compose:{ LATEST_VERSION }'
	}
 ```

# License
GalleryDialog is available under the MIT license. Read the [LICENSE.txt](LICENSE.txt) file for more information.
