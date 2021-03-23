package com.an9ar.bookkeeper.screens

sealed class Screens(val routeName: String) {
    object CollectionScreen : Screens(routeName = "CollectionScreen")
    object BookAddScreen : Screens(routeName = "BookAddScreen")
    object BookInfoScreen : Screens(routeName = "BookInfoScreen")
    object MenuScreen : Screens(routeName = "MenuScreen")
    object SettingsScreen : Screens(routeName = "SettingsScreen")
    object CreditsScreen : Screens(routeName = "CreditsScreen")
}