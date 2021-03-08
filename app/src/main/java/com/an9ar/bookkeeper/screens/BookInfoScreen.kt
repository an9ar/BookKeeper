package com.an9ar.bookkeeper.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.an9ar.bookkeeper.viewmodels.MainViewModel

@Composable
fun BookInfoScreen(
    bookId: String,
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    Scaffold(
        topBar = {
            BookScreenToolbar(
                title = "Edit book info",
                navHostController = navHostController
            )
        }
    ) {
        BookInfoScreenContent(
            navHostController = navHostController,
            mainViewModel = mainViewModel
        )
    }
}

@Composable
fun BookInfoScreenContent(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {

}
