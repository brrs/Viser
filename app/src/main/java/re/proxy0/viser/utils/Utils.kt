package re.proxy0.viser.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import re.proxy0.viser.data.UIPreferences
import java.util.*

fun randomColor(): Color {
    val rnd = Random()
    return Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
}

@Composable
fun isDarkTheme(): Boolean {
    val preferences = UIPreferences(LocalContext.current)
    return preferences.isDarkMode.collectAsState(initial = false).value
}
