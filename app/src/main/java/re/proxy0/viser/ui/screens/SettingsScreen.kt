package re.proxy0.viser.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import re.proxy0.viser.data.UIPreferences

@Composable
fun SettingsScreen(navController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Header(navController)
        Column {
            SwitchItem()
        }
    }
}

@Composable
fun SwitchItem() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val preferences = UIPreferences(context)
    val darkMode = preferences.isDarkMode.collectAsState(initial = false).value

    Box(
        Modifier
            .padding(horizontal = 16.dp)
            .height(64.dp)
            .fillMaxWidth()
    ) {
        Switch(
            modifier = Modifier.align(Alignment.CenterEnd),
            checked = darkMode,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.secondary,
                checkedTrackColor = MaterialTheme.colors.onSecondary,
            ),
            onCheckedChange = {
                scope.launch {
                    preferences.saveDarkMode(!darkMode)
                }
            }
        )
        Text(
            text = "Night theme",
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}

@Composable
fun Header(navController: NavHostController) {
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

        Text(
            text = "Settings",
            style = MaterialTheme.typography.h5,
            fontFamily = FontFamily.SansSerif,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 56.dp)
        )
    }
}