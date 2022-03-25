package re.proxy0.viser.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import re.proxy0.viser.R
import re.proxy0.viser.data.BookCollection
import re.proxy0.viser.data.generateCollection
import re.proxy0.viser.ui.components.BookCollectionList
import re.proxy0.viser.ui.theme.ViserTheme

@Composable
fun LibraryScreen(navController: NavController) {
    Column(
        Modifier
            .background(ViserTheme.colors.surface)
            .fillMaxSize()
    ) {
        Header(navController)
        MainContent(List(3) { generateCollection(10) }, navController)
    }
}

@Composable
fun Header(navController: NavController) {
    Box(
        Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(ViserTheme.colors.leading)
    ) {
        IconButton(
            onClick = { navController.navigate(ScreenList.Settings.route) },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                tint = ViserTheme.colors.onLeading
            )
        }
        IconButton(onClick = { }, modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = ViserTheme.colors.onLeading
            )
        }

        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 24.sp,
            fontFamily = FontFamily.SansSerif,
            color = ViserTheme.colors.onLeading,
            modifier = Modifier.align(Alignment.Center)
        )
        Divider(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            color = ViserTheme.colors.background
        )
    }
}

@Composable
fun MainContent(collections: List<BookCollection>, navController: NavController) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        collections.forEach { bookCollection ->
            BookCollectionList(
                bookCollection = bookCollection,
                onBookClick = { navController.navigate(ScreenList.BookDetails.route) }
            )
        }
    }
}