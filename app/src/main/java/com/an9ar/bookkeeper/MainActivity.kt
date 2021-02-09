package com.an9ar.bookkeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.an9ar.bookkeeper.theme.BookKeeperTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookKeeperTheme {
                Surface(color = MaterialTheme.colors.background) {

                }
            }
        }
    }
}