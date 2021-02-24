package com.an9ar.bookkeeper.screens

sealed class Screens(val routeName: String) {
    object SplashScreen : Screens(routeName = "SplashScreen")
    object CollectionScreen : Screens(routeName = "CollectionScreen")
    object BookAddScreen : Screens(routeName = "BookAddScreen")
    object MenuScreen : Screens(routeName = "MenuScreen")
    object CreditsScreen : Screens(routeName = "CreditsScreen")
}