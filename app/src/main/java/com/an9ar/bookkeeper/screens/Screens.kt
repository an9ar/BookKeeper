package com.an9ar.bookkeeper.screens

sealed class Screens(val routeName: String) {
    object CollectionScreen : Screens(routeName = "CollectionScreen")
}