package com.an9ar.bookkeeper.screens

sealed class Screens(val routeName: String) {
    object SplashScreen : Screens(routeName = "SplashScreen")
    object CollectionScreen : Screens(routeName = "CollectionScreen")
    object MenuScreen : Screens(routeName = "MenuScreen")
}