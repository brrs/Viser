package re.proxy0.viser.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import re.proxy0.viser.data.UIPreferences

private val LightColorPalette = ViserColors(
    leading = Gray1,
    onLeading = Gray6,
    onLeadingSecondary = Gray5,

    background = Gray2,
    onBackground = Gray6,
    onBackgroundSecondary = Gray5,

    surface = Gray1,
    onSurface = Gray6,
    onSurfaceSecondary = Gray5,

    accessory = Gray3,
    onAccessory = Gray6,
    onAccessorySecondary = Gray5,

    accessoryVariant = Gray4,
    onAccessoryVariant = Gray1,
    onAccessoryVariantSecondary = Gray1,

    accent = Blue1,
    onAccent = Gray1,
    onAccentSecondary = Gray2,

    accentVariant = Red2,
    onAccentVariant = Gray6,
    onAccentVariantSecondary = Gray5,

    brand = Teal3,

    statusCompleted = Green1,
    statusOngoing = Yellow4
)

private val DarkColorPalette = ViserColors(
    leading = Gray8,
    onLeading = Gray1,
    onLeadingSecondary = Gray3,

    background = Gray7,
    onBackground = Gray1,
    onBackgroundSecondary = Gray3,

    surface = Gray8,
    onSurface = Gray1,
    onSurfaceSecondary = Gray3,

    accessory = Gray6,
    onAccessory = Gray1,
    onAccessorySecondary = Gray3,

    accessoryVariant = Gray5,
    onAccessoryVariant = Gray1,
    onAccessoryVariantSecondary = Gray3,

    accent = Blue1,
    onAccent = Gray1,
    onAccentSecondary = Gray2,

    accentVariant = Red2,
    onAccentVariant = Gray6,
    onAccentVariantSecondary = Gray5,

    brand = Teal3,

    statusCompleted = Green1,
    statusOngoing = Yellow3
)

@Composable
fun ViserTheme(
    content: @Composable () -> Unit
) {
    val preferences = UIPreferences(LocalContext.current)
    val darkTheme = preferences.darkMode.collectAsState(initial = true).value

    val colors = if (darkTheme) {
        DarkColorPalette
//        LightColorPalette
    } else {
        LightColorPalette
    }

    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = colors.leading
        )
    }

    ProvideViserColors(colors) {
        MaterialTheme(
            colors = debugColors(darkTheme),
            typography = Typography,
            shapes = Shapes,
        ) {
            CompositionLocalProvider(
                LocalRippleTheme provides ViserRippleTheme, content = content
            )
        }
    }
}

object ViserTheme {
    val colors: ViserColors
        @Composable
        get() = LocalViserColors.current
}

@Composable
fun ProvideViserColors(
    colors: ViserColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors.copy() }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalViserColors provides colorPalette, content = content)
}

private val LocalViserColors = staticCompositionLocalOf<ViserColors> {
    error("No ViserColorPalette provided")
}

@Stable
class ViserColors(
    leading: Color,
    onLeading: Color,
    onLeadingSecondary: Color,

    background: Color,
    onBackground: Color,
    onBackgroundSecondary: Color,

    surface: Color,
    onSurface: Color,
    onSurfaceSecondary: Color,

    accessory: Color,
    onAccessory: Color,
    onAccessorySecondary: Color,

    accessoryVariant: Color,
    onAccessoryVariant: Color,
    onAccessoryVariantSecondary: Color,

    accent: Color,
    onAccent: Color,
    onAccentSecondary: Color,

    accentVariant: Color,
    onAccentVariant: Color,
    onAccentVariantSecondary: Color,

    brand: Color,

    statusCompleted: Color,
    statusOngoing: Color,
) {
    var leading by mutableStateOf(leading)
        private set
    var onLeading by mutableStateOf(onLeading)
        private set
    var onLeadingSecondary by mutableStateOf(onLeadingSecondary)
        private set

    var background by mutableStateOf(background)
        private set
    var onBackground by mutableStateOf(onBackground)
        private set
    var onBackgroundSecondary by mutableStateOf(onBackgroundSecondary)
        private set

    var surface by mutableStateOf(surface)
        private set
    var onSurface by mutableStateOf(onSurface)
        private set
    var onSurfaceSecondary by mutableStateOf(onSurfaceSecondary)
        private set

    var accessory by mutableStateOf(accessory)
        private set
    var onAccessory by mutableStateOf(onAccessory)
        private set
    var onAccessorySecondary by mutableStateOf(onAccessorySecondary)
        private set

    var accessoryVariant by mutableStateOf(accessoryVariant)
        private set
    var onAccessoryVariant by mutableStateOf(onAccessoryVariant)
        private set
    var onAccessoryVariantSecondary by mutableStateOf(onAccessoryVariantSecondary)
        private set

    var accent by mutableStateOf(accent)
        private set
    var onAccent by mutableStateOf(onAccent)
        private set
    var onAccentSecondary by mutableStateOf(onAccentSecondary)
        private set

    var accentVariant by mutableStateOf(accentVariant)
        private set
    var onAccentVariant by mutableStateOf(onAccentVariant)
        private set
    var onAccentVariantSecondary by mutableStateOf(onAccentVariantSecondary)
        private set

    var brand by mutableStateOf(brand)
        private set

    var statusCompleted by mutableStateOf(statusCompleted)
        private set
    var statusOngoing by mutableStateOf(statusOngoing)
        private set

    fun update(colors: ViserColors) {
        leading = colors.leading
        onLeading = colors.onLeading
        onLeadingSecondary = colors.onLeadingSecondary

        background = colors.background
        onBackground = colors.onBackground
        onBackgroundSecondary = colors.onBackgroundSecondary

        surface = colors.surface
        onSurface = colors.onSurface
        onSurfaceSecondary = colors.onSurfaceSecondary

        accessory = colors.accessory
        onAccessory = colors.onAccessory
        onAccessorySecondary = colors.onAccessorySecondary

        accessoryVariant = colors.accessoryVariant
        onAccessoryVariant = colors.onAccessoryVariant
        onAccessoryVariantSecondary = colors.onAccessoryVariantSecondary

        accent = colors.accent
        onAccent = colors.onAccent
        onAccentSecondary = colors.onAccentSecondary

        accentVariant = colors.accentVariant
        onAccentVariant = colors.onAccentVariant
        onAccentVariantSecondary = colors.onAccentVariantSecondary

        brand = colors.brand

        statusCompleted = colors.statusCompleted
        statusOngoing = colors.statusOngoing
    }

    fun copy(): ViserColors = ViserColors(
        leading = leading,
        onLeading = onLeading,
        onLeadingSecondary = onLeadingSecondary,

        background = background,
        onBackground = onBackground,
        onBackgroundSecondary = onBackgroundSecondary,

        surface = surface,
        onSurface = onSurface,
        onSurfaceSecondary = onSurfaceSecondary,

        accessory = accessory,
        onAccessory = onAccessory,
        onAccessorySecondary = onAccessorySecondary,

        accessoryVariant = accessoryVariant,
        onAccessoryVariant = onAccessoryVariant,
        onAccessoryVariantSecondary = onAccessoryVariantSecondary,

        accent = accent,
        onAccent = onAccent,
        onAccentSecondary = onAccentSecondary,

        accentVariant = accentVariant,
        onAccentVariant = onAccentVariant,
        onAccentVariantSecondary = onAccentVariantSecondary,

        brand = brand,

        statusCompleted = statusCompleted,
        statusOngoing = statusOngoing,
    )
}

fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)

private object ViserRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = ViserTheme.colors.onLeading

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        Color.Black,
        lightTheme = !isSystemInDarkTheme()
    )
}