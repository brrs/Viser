package re.proxy0.viser.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import re.proxy0.viser.R
import re.proxy0.viser.ui.theme.OneMoreColor
import re.proxy0.viser.ui.theme.Transparent
import re.proxy0.viser.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun ReaderScreen(navController: NavHostController) {
    BottomSheetScaffold(navController)
}

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun BottomSheetScaffold(navController: NavHostController) {
    val bottomState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()
    val spacingModeState = remember { mutableStateOf(1) }
    val textSizeState = remember { mutableStateOf(16) }
    val scrollState = rememberScrollState()
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }



    ModalBottomSheetLayout(
        sheetState = bottomState,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        sheetContent = {
            BottomSheetContent(spacingMode = spacingModeState, textSizeState = textSizeState)
        },
        sheetBackgroundColor = MaterialTheme.colors.surface,
        sheetElevation = 16.dp,
        sheetShape = RoundedCornerShape(24.dp, 24.dp),
        scrimColor = Color.Unspecified
    ) {
        Scaffold(
            /*floatingActionButton = {
                AdjustTextFab(
                    scope = coroutineScope,
                    bottomSheetScaffoldState = bottomState,
                    isVisible = scrollState.value == 0
                )
            },*/
            content = {
                MainContent(
                    navController = navController,
                    scrollState = scrollState,
                    spacingMode = spacingModeState,
                    textSizeState = textSizeState,
                    scope = coroutineScope,
                    bottomSheetScaffoldState = bottomState
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    navController: NavHostController,
    spacingMode: MutableState<Int>,
    textSizeState: MutableState<Int>,
    scrollState: ScrollState,
    scope: CoroutineScope,
    bottomSheetScaffoldState: ModalBottomSheetState
) {
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize(),
    ) {
        Header(
            navController,
            scope,
            bottomSheetScaffoldState
        )
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.article_title),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            val spacingHeight = when (spacingMode.value) {
                1 -> 20.sp
                2 -> 28.sp
                3 -> 36.sp
                else -> {
                    20.sp
                }
            }
            Text(
                stringResource(id = R.string.longText),
                lineHeight = spacingHeight,
                fontSize = textSizeState.value.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Header(
    navController: NavHostController,
    scope: CoroutineScope,
    bottomSheetScaffoldState: ModalBottomSheetState
) {
    Box(
        Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
    ) {
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary
            )
        }
        IconButton(
            onClick = {
                scope.launch {
                    if (!bottomSheetScaffoldState.isVisible) {
                        bottomSheetScaffoldState.show()
                    } else {
                        bottomSheetScaffoldState.hide()
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_adjust_text),
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Composable
fun BottomSheetContent(spacingMode: MutableState<Int>, textSizeState: MutableState<Int>) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(MaterialTheme.colors.primaryVariant)
            .padding(8.dp, 0.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_horizontal_rule),
            contentDescription = "",
            Modifier
                .fillMaxWidth()
                .height(48.dp)
                .align(Alignment.CenterHorizontally),
        )
        FontSizeButtons(textSizeState)
        Spacer(modifier = Modifier.height(16.dp))
        LineSpacingButtons(spacingMode)
    }
}

/*@ExperimentalMaterialApi
@Composable
fun AdjustTextFab(
    scope: CoroutineScope,
    bottomSheetScaffoldState: ModalBottomSheetState,
    isVisible: Boolean
) {
    if (isVisible) FloatingActionButton(
        onClick = {
            scope.launch {
                if (!bottomSheetScaffoldState.isVisible) {
                    bottomSheetScaffoldState.show()
                } else {
                    bottomSheetScaffoldState.hide()
                }
            }
        },
        modifier = Modifier
            .size(64.dp),
        ) {
        Icon(painter = painterResource(id = R.drawable.ic_adjust_text), "", modifier = Modifier.size(32.dp))
    }
}*/

@Composable
fun FontSizeButtons(textSizeState: MutableState<Int>) {
    Row {
        TextButton(
            onClick = {
                if (textSizeState.value >= 14) textSizeState.value--
            },
            Modifier
                .fillMaxWidth()
                .height(64.dp)
                .weight(1f),
            shape = RoundedCornerShape(25, 0, 0, 25),
            colors = ButtonDefaults.buttonColors(backgroundColor = OneMoreColor)
        ) {
            Text(
                "A",
                fontSize = 16.sp,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        TextButton(
            onClick = { if (textSizeState.value <= 32) textSizeState.value++ },
            Modifier
                .fillMaxWidth()
                .height(64.dp)
                .weight(1f),
            shape = RoundedCornerShape(0, 25, 25, 0),
            colors = ButtonDefaults.buttonColors(backgroundColor = OneMoreColor)
        ) {
            Text(
                "A",
                fontSize = 32.sp,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun LineSpacingButtons(spacingMode: MutableState<Int>) {
    Row {
        LineSpacingButton(
            painter = painterResource(id = R.drawable.ic_line_spacing_min),
            isChosen = spacingMode.value == 1,
            onClick = { spacingMode.value = 1 },
            modifier = Modifier.weight(1f)
        )

        LineSpacingButton(
            painter = painterResource(id = R.drawable.ic_line_spacing_mid),
            isChosen = spacingMode.value == 2,
            onClick = { spacingMode.value = 2 },
            modifier = Modifier.weight(1f)
        )

        LineSpacingButton(
            painter = painterResource(id = R.drawable.ic_line_spacing_max),
            isChosen = spacingMode.value == 3,
            onClick = { spacingMode.value = 3 },
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
        border = if (isChosen) BorderStroke(1.dp, White) else null
    ) {
        Icon(
            modifier = modifier
                .padding(8.dp),
            painter = painter,
            contentDescription = ""
        )
    }
}
