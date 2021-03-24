package com.an9ar.bookkeeper.screens

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.an9ar.bookkeeper.theme.AppTheme
import com.an9ar.bookkeeper.viewmodels.MainViewModel
import io.realm.Realm

@Composable
fun MainNavScreen(
    mainViewModel: MainViewModel,
    realmObject: Realm
) {
    Surface(color = AppTheme.colors.background) {
        val navHostController = rememberNavController()

        NavHost(navController = navHostController, startDestination = Screens.CollectionScreen.routeName) {
            composable(Screens.CollectionScreen.routeName) {
                CollectionScreen(navHostController = navHostController, mainViewModel = mainViewModel)
            }
            composable(Screens.BookAddScreen.routeName) {
                BookAddScreen(navHostController = navHostController, mainViewModel = mainViewModel)
            }
            composable("${Screens.BookInfoScreen.routeName}/{bookId}") { backStackEntry ->
                backStackEntry.arguments?.getString("bookId")?.let { bookId ->
                    BookInfoScreen(
                        bookId = bookId,
                        realmObject = realmObject,
                        navHostController = navHostController,
                        mainViewModel = mainViewModel)
                }
            }
            composable(Screens.MenuScreen.routeName) {
                MenuScreen(navHostController = navHostController)
            }
            composable(Screens.SettingsScreen.routeName) {
                SettingsScreen(navHostController = navHostController)
            }
            composable(Screens.CreditsScreen.routeName) {
                CreditsScreen(navHostController = navHostController)
            }
        }
    }
}