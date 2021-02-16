package com.an9ar.bookkeeper.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.an9ar.bookkeeper.theme.AppTheme
import com.an9ar.bookkeeper.viewmodels.MainViewModel
import dev.chrisbanes.accompanist.insets.LocalWindowInsets
import dev.chrisbanes.accompanist.insets.toPaddingValues

@Composable
fun CollectionScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    Scaffold(
        topBar = {
            CollectionScreenToolbar(navHostController = navHostController)
        }
    ) {
        CollectionScreenContent(
            navHostController = navHostController,
            mainViewModel = mainViewModel
        )
    }
}

@Composable
fun CollectionScreenContent(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {

}

@Composable
fun CollectionScreenToolbar(
    navHostController: NavHostController
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                LocalWindowInsets.current.systemBars
                    .toPaddingValues(
                        bottom = false,
                        additionalTop = 8.dp,
                        additionalBottom = 8.dp
                    )
            )
    ) {
        val (menuButton, titleBlock, addButton) = createRefs()

        IconButton(
            onClick = {

            },
            modifier = Modifier.constrainAs(menuButton) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start, 8.dp)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu button",
                tint = AppTheme.colors.uiSurface
            )
        }
        Text(
            text = "Books collections",
            color = AppTheme.colors.text,
            style = AppTheme.typography.h6,
            modifier = Modifier.constrainAs(titleBlock) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        IconButton(
            onClick = {

            },
            modifier = Modifier.constrainAs(addButton) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end, 8.dp)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add button",
                tint = AppTheme.colors.uiSurface
            )
        }
    }
}