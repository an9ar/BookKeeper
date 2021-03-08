package com.an9ar.bookkeeper.screens

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.an9ar.bookkeeper.theme.AppTheme
import com.an9ar.bookkeeper.viewmodels.MainViewModel

@Composable
fun MainNavScreen(mainViewModel: MainViewModel) {
    Surface(color = AppTheme.colors.background) {
        val navHostController = rememberNavController()

        NavHost(navController = navHostController, startDestination = Screens.CollectionScreen.routeName) {
            composable(Screens.CollectionScreen.routeName) {
                CollectionScreen(navHostController = navHostController, mainViewModel = mainViewModel)
            }
            composable(Screens.BookAddScreen.routeName) {
                BookAddScreen(navHostController = navHostController, mainViewModel = mainViewModel)
            }
            composable(
                Screens.BookInfoScreen.routeName,
                arguments = listOf(navArgument("bookId") { type = NavType.StringType })
            ) { backStackEntry ->
                backStackEntry.arguments?.getString("bookId")?.let { bookId ->
                    BookInfoScreen(bookId = bookId, navHostController = navHostController, mainViewModel = mainViewModel)
                }
            }
            composable(Screens.MenuScreen.routeName) {
                MenuScreen(navHostController = navHostController)
            }
            composable(Screens.CreditsScreen.routeName) {
                CreditsScreen(navHostController = navHostController)
            }
        }
    }
}