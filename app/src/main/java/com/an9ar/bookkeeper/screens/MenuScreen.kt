package com.an9ar.bookkeeper.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.an9ar.bookkeeper.R
import com.an9ar.bookkeeper.theme.AppTheme
import dev.chrisbanes.accompanist.insets.LocalWindowInsets
import dev.chrisbanes.accompanist.insets.toPaddingValues

@Composable
fun MenuScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            ConstraintLayout(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalWindowInsets.current.statusBars.toPaddingValues())
                .preferredHeight(AppTheme.sizes.appBarHeight)
            ) {
                val (screenTitle, backButton) = createRefs()
                Text(
                    text = "Main menu",
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
        MenuScreenContent(navHostController = navHostController)
    }
}

@Composable
fun MenuScreenContent(
    navHostController: NavHostController
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(AppTheme.colors.background)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxSize()
                .weight(0.3f)
                .background(AppTheme.colors.card)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "App logo",
                colorFilter = ColorFilter.tint(AppTheme.colors.text),
                modifier = Modifier.preferredSize(64.dp)
            )
        }
        Divider(
            color = AppTheme.colors.card,
            modifier = Modifier.padding(16.dp)
        )
        val menuItemsList = listOf(
            "Settings" to {  },
            "Credits" to { navHostController.navigate(Screens.CreditsScreen.routeName) },
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
                .weight(0.7f)
        ) {
            items(menuItemsList) { menuItem ->
                MenuListItem(
                    itemTitle = menuItem.first,
                    onClickAction = menuItem.second
                )
            }
        }
    }
}

@Composable
fun MenuListItem(
    itemTitle: String,
    onClickAction: () -> Unit
) {
    Card(
        backgroundColor = AppTheme.colors.card,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable(onClick = onClickAction)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = itemTitle,
                color = AppTheme.colors.text,
                textAlign = TextAlign.Center,
                style = AppTheme.typography.h6,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }
}