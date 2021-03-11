package com.an9ar.bookkeeper.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.an9ar.bookkeeper.data.types.BookType
import com.an9ar.bookkeeper.theme.AppTheme

@Composable
fun TransparentBottomBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = AppTheme.colors.transparent,
        modifier = modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}

@Composable
fun BookKeeperBottomNavigation(
    navHostController: NavHostController,
    onTabClicked: (BookType) -> Unit
) {
    val navItems = listOf(
        BookType.IN_PROGRESS,
        BookType.READING_LIST,
        BookType.READ
    )
    TransparentBottomBar {
        var currentRoute by remember { mutableStateOf(navItems.first()) }
        navItems.forEach { tab ->
            val isSelected = currentRoute == tab
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = null,
                        tint = if (isSelected) AppTheme.colors.bottomNavItem else AppTheme.colors.bottomNavItem.copy(
                            alpha = ContentAlpha.medium
                        )
                    )
                },
                selectedContentColor = AppTheme.colors.bottomNavItem,
                unselectedContentColor = AppTheme.colors.bottomNavItem.copy(alpha = ContentAlpha.medium),
                label = {
                    Text(
                        text = tab.title,
                        style = if (isSelected) AppTheme.typography.bottomNavItemBoldTitle else AppTheme.typography.bottomNavItemTitle,
                    )
                },
                selected = isSelected,
                onClick = {
                    currentRoute = tab
                    onTabClicked(tab)
                }
            )
        }
    }
}