package re.proxy0.viser.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Black,    //tool bar
    primaryVariant = NightColorPrimaryVariant,   //bottom sheet
    secondary = NightColorSecondary,    //???
    secondaryVariant = NightColorSecondaryVariant,  //???
    background = Black,   //main screen background
    surface = Black,  //options menu

    onPrimary = NightColorOnPrimary,
    onBackground = NightColorOnPrimary,
    onSecondary = NightColorOnPrimary,
    onSurface = NightColorOnPrimary,
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,
*/
)

@Composable
fun ViserTheme(darkTheme: Boolean = true /*isSystemInDarkTheme()*/, content: @Composable() () -> Unit) {
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