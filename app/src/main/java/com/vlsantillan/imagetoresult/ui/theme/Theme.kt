package com.vlsantillan.imagetoresult.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.vlsantillan.imagetoresult.BuildConfig

private val LightColorPalette = lightColors(
    primary = if (BuildConfig.FLAVOR_theme == "green") Color.Green else Color.Red,
    primaryVariant = Purple700,
    secondary = Teal200,
    onPrimary = if (BuildConfig.FLAVOR_theme == "green") Color.Black else Color.White

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ImageToResultTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}