package re.proxy0.viser.utils

import androidx.compose.ui.graphics.Color
import java.util.*

fun randomColor(): Color {
    val rnd = Random()
    return Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
}