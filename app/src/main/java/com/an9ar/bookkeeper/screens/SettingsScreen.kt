package com.an9ar.bookkeeper.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.an9ar.bookkeeper.theme.AppTheme
import com.an9ar.bookkeeper.theme.LocalStrings
import com.an9ar.bookkeeper.theme.englishLocale
import com.an9ar.bookkeeper.theme.russianLocale
import dev.chrisbanes.accompanist.insets.LocalWindowInsets
import dev.chrisbanes.accompanist.insets.toPaddingValues

@Composable
fun SettingsScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            ConstraintLayout(modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.toolbar)
                .padding(LocalWindowInsets.current.statusBars.toPaddingValues())
                .height(AppTheme.sizes.appBarHeight)
            ) {
                val (screenTitle, backButton) = createRefs()
                Text(
                    text = AppTheme.strings.settings,
                    style = AppTheme.typography.h6,
                    color = AppTheme.colors.text,
                    modifier = Modifier.constrainAs(screenTitle) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
                IconButton(
                    onClick = { navHostController.navigateUp() },
                    modifier = Modifier
                        .constrainAs(backButton) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = AppTheme.colors.uiSurface
                    )
                }
            }
        }
    ) {
        SettingsScreenContent()
    }
}

@Composable
fun SettingsScreenContent() {
    val localeItemsList = listOf(
        "English" to englishLocale(),
        "Russian" to russianLocale(),
    )
    LazyColumn(
        contentPadding = LocalWindowInsets.current.navigationBars
            .toPaddingValues(
                bottom = false,
                additionalTop = AppTheme.sizes.small,
                additionalBottom = AppTheme.sizes.bottomNavigationHeight
            ),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(localeItemsList) { menuItem ->
            MenuListItem(
                itemTitle = menuItem.first,
                onClickAction = {

                }
            )
        }
    }
}