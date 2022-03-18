package re.proxy0.viser.ui.screens.reader

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import re.proxy0.viser.R
import re.proxy0.viser.data.UIPreferences
import re.proxy0.viser.ui.screens.TextFormattingScreen
import re.proxy0.viser.ui.theme.Black
import re.proxy0.viser.ui.theme.White


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ReaderScreen(navController: NavHostController) {
    val viewModel: ReaderViewModel = viewModel()
    BottomSheetScaffold(navController, viewModel)
}

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun BottomSheetScaffold(navController: NavHostController, viewModel: ReaderViewModel) {

    val bottomSheetScaffoldState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    ModalBottomSheetLayout(
        sheetState = bottomSheetScaffoldState,
        modifier = Modifier.nestedScroll(TopAppBarDefaults.pinnedScrollBehavior().nestedScrollConnection),
        sheetContent = {
            BottomSheetContent(viewModel)
        },
        sheetBackgroundColor = MaterialTheme.colors.surface,
        sheetElevation = 16.dp,
        sheetShape = RoundedCornerShape(24.dp, 24.dp),
        scrimColor = Color.Unspecified
    ) {
        Scaffold {
            MainContent(
                navController = navController,
                viewModel = viewModel,
                onTextFormatting = uiActionOnTextFormatting(
                    coroutineScope = rememberCoroutineScope(),
                    bottomSheetScaffoldState = bottomSheetScaffoldState
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
fun uiActionOnTextFormatting(
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: ModalBottomSheetState
): () -> Unit = {
    coroutineScope.launch {
        if (!bottomSheetScaffoldState.isVisible) {
            bottomSheetScaffoldState.show()
        } else {
            bottomSheetScaffoldState.hide()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MainContent(
    navController: NavHostController,
    viewModel: ReaderViewModel,
    onTextFormatting: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    val uiPreferences = UIPreferences(LocalContext.current)
    val darkMode = uiPreferences.darkMode.collectAsState(false).value
    val backgroundColor = if (darkMode) Black else White
    Box(Modifier.background(backgroundColor)) {
        var showTopBar by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .verticalScroll(rememberScrollState())
                .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                    showTopBar = !showTopBar
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(64.dp))

            Text(
                text = viewState.title,
                fontSize = (viewState.fontSize + 2).sp,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(32.dp))

            val customTextSelectionColors = TextSelectionColors(
                handleColor = MaterialTheme.colors.secondary,
                backgroundColor = MaterialTheme.colors.secondary.copy(alpha = 0.4f)
            )
            CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                SelectionContainer {
                    Text(
                        text = viewState.text,
                        fontSize = viewState.fontSize.sp,
                        lineHeight = (viewState.fontSize * viewState.lineSpacing).sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(128.dp))
        }

        Header(
            navController = navController,
            showTopBar = showTopBar,
            onTextFormatting = onTextFormatting,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .animateContentSize()
                .clickable(enabled = false) { }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Header(
    navController: NavHostController,
    onTextFormatting: () -> Unit,
    showTopBar: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = showTopBar,
        enter = fadeIn(tween(200)),
        exit = fadeOut(tween(200))
    ) {
        Box(
            modifier
                .height(56.dp)
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
                onClick = onTextFormatting,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_adjust_text),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
            }
            Text(
                text = "Chapter 1. Today I was born.",
                fontSize = 22.sp,
                fontFamily = FontFamily.SansSerif,
                color = MaterialTheme.colors.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 56.dp)
            )
        }
    }
}

@Composable
fun BottomSheetContent(viewModel: ReaderViewModel) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(MaterialTheme.colors.background)
            .padding(8.dp, 0.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_horizontal_rule),
            contentDescription = "",
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .align(Alignment.CenterHorizontally),
        )
        TextFormattingScreen(viewModel)
    }
}
