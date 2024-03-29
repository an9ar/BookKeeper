package com.an9ar.bookkeeper.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*

@Composable
fun BookKeeperTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: AppTypography = AppTypography(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) darkColorPalette() else lightColorPalette()
    CompositionLocalProvider(
        LocalColor provides colors,
        LocalTypography provides typography,
        LocalStrings provides russianLocale(),
    ) {
        MaterialTheme(
            colors = colors.materialColors,
            typography = typography.materialTypography
        ) {
            content()
        }
    }
}

object AppTheme {

    val colors: ColorPalette
        @Composable get() = LocalColor.current

    val typography: AppTypography
        @Composable get() = LocalTypography.current

    val sizes: AppSizes
        @Composable get() = AppSizes()

    val strings: AppStrings
        @Composable get() = LocalStrings.current
}

internal val LocalColor = staticCompositionLocalOf { lightColorPalette() }
internal val LocalTypography = staticCompositionLocalOf { AppTypography() }
internal val LocalStrings = staticCompositionLocalOf { englishLocale() }
