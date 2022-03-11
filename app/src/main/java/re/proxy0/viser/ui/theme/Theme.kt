package re.proxy0.viser.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import re.proxy0.viser.data.UIPreferences

private val DarkColorPalette = darkColors(
    primary = Gray4,
    primaryVariant = Gray4,
    onPrimary = White,
    secondary = Teal3,
    secondaryVariant = Gray2,
    onSecondary = White,
    error = Red1,
    onError = White,
    background = Gray4,
    onBackground = White,
    surface = Gray1,
    onSurface = White,
)

private val LightColorPalette = lightColors(
    primary = Yellow3,
    primaryVariant = Yellow4,
    onPrimary = Black,
    secondary = Teal1,
    secondaryVariant = Teal3,
    onSecondary = White,
    error = Red1,
    onError = White,
    background = White,
    onBackground = Black,
    surface = Yellow2,
    onSurface = Black
)

@Composable
fun ViserTheme(
    content: @Composable () -> Unit
) {
    val preferences = UIPreferences(LocalContext.current)
    val darkTheme = preferences.isDarkMode.collectAsState(initial = false).value

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}