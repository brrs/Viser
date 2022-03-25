package re.proxy0.viser.ui.screens.reader

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import re.proxy0.viser.R
import re.proxy0.viser.ui.theme.Transparent
import re.proxy0.viser.ui.theme.ViserTheme
import kotlin.math.roundToInt

@Composable
fun TextFormattingScreen(
    viewModel: ReaderViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp, 0.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        TextScaling(viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        LineSpacingButtons(viewModel)
    }
}

@Composable
fun TextScaling(viewModel: ReaderViewModel) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            "T",
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            color = ViserTheme.colors.onBackgroundSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterStart)
        )

        val fontSize = viewModel.state.collectAsState().value.fontSize
        var positionState by remember {
            mutableStateOf(fontSizeToSliderPosition(fontSize))
        }
        Slider(
            modifier = Modifier
                .padding(horizontal = 54.dp)
                .align(Alignment.Center),
            steps = 12,
            valueRange = 1f..14f,
            value = positionState,
            onValueChange = {
                positionState = it
                viewModel.saveFontSize(sliderPositionToFontSize(it))
            },
            colors = SliderDefaults.colors(
                thumbColor = ViserTheme.colors.accent,

                activeTrackColor = ViserTheme.colors.onBackgroundSecondary,
                activeTickColor = ViserTheme.colors.onBackgroundSecondary,

                inactiveTrackColor = ViserTheme.colors.onBackgroundSecondary.copy(0.4f),
                inactiveTickColor = ViserTheme.colors.onBackgroundSecondary.copy(0.4f),
            )
        )

        Text(
            "T",
            fontSize = 32.sp,
            color = ViserTheme.colors.onBackgroundSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

fun sliderPositionToFontSize(position: Float): Int {
    return when (position.roundToInt()) {
        0 -> 11
        1 -> 12
        2 -> 13
        3 -> 14
        4 -> 15
        5 -> 16
        6 -> 18
        7 -> 20
        8 -> 22
        9 -> 24
        10 -> 26
        11 -> 28
        12 -> 30
        13 -> 32
        14 -> 34
        else -> 16
    }
}

fun fontSizeToSliderPosition(fontSize: Int): Float {
    return when (fontSize) {
        11 -> 0
        12 -> 1
        13 -> 2
        14 -> 3
        15 -> 4
        16 -> 5
        18 -> 6
        20 -> 7
        22 -> 8
        24 -> 9
        26 -> 10
        28 -> 11
        30 -> 12
        32 -> 13
        34 -> 14
        else -> 5
    }.toFloat()
}


private const val LineSpacingMin = 1.25f
private const val LineSpacingMid = 1.5f
private const val LineSpacingMax = 2f

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LineSpacingButtons(viewModel: ReaderViewModel) {
    Row {
        val currentLineSpacing = viewModel.state.collectAsState().value.lineSpacing
        LineSpacingButton(
            painter = painterResource(id = R.drawable.ic_line_spacing_min),
            isChosen = currentLineSpacing == LineSpacingMin,
            onClick = { viewModel.saveLineSpacing(LineSpacingMin) },
            modifier = Modifier.weight(1f)
        )

        LineSpacingButton(
            painter = painterResource(id = R.drawable.ic_line_spacing_mid),
            isChosen = currentLineSpacing == LineSpacingMid,
            onClick = { viewModel.saveLineSpacing(LineSpacingMid) },
            modifier = Modifier.weight(1f)
        )

        LineSpacingButton(
            painter = painterResource(id = R.drawable.ic_line_spacing_max),
            isChosen = currentLineSpacing == LineSpacingMax,
            onClick = { viewModel.saveLineSpacing(LineSpacingMax) },
            modifier = Modifier.weight(1f)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LineSpacingButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    isChosen: Boolean = false,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .requiredSize(64.dp)
            .clipToBounds(),
        shape = RoundedCornerShape(25),
        onClick = { onClick.invoke() },
        color = Transparent,
        border = if (isChosen) BorderStroke(1.dp, ViserTheme.colors.onBackgroundSecondary) else null
    ) {
        Icon(
            modifier = modifier
                .padding(8.dp),
            painter = painter,
            contentDescription = "",
            tint = if (isChosen) ViserTheme.colors.onSurface else ViserTheme.colors.onBackgroundSecondary
        )
    }
}


