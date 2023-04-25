package com.swbvelasquez.complextestingandroidktx

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Esta anotacion activa la generación de código de Hilt, incluida una clase base para tu aplicación que sirve como contenedor de dependencia a nivel de la aplicación.
@HiltAndroidApp
class ShoppingApplication : Application() {
}