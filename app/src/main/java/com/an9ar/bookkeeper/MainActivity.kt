package com.an9ar.bookkeeper

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.an9ar.bookkeeper.screens.MainNavScreen
import com.an9ar.bookkeeper.theme.BookKeeperTheme
import com.an9ar.bookkeeper.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookKeeperTheme {
                ProvideWindowInsets {
                    MainNavScreen(mainViewModel = mainViewModel)
                }
            }
        }
    }
}