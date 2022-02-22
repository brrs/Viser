package re.proxy0.viser.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import re.proxy0.viser.R
import re.proxy0.viser.data.BookCollection
import re.proxy0.viser.data.generateCollection
import re.proxy0.viser.ui.components.BookCollectionList

@Composable
fun LibraryScreen(navController: NavController) {
    Column {
        Header()
        MainContent(List(3) { generateCollection(10) }, navController)
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun Preview() {
    MaterialTheme {
        Column {
            Header()
            //MainContent(List(2) { generateCollection(10) })
        }
    }
}

@Composable
fun Header() {
    Box(
        Modifier
            .height(56.dp)
            .fillMaxWidth()
    ) {
        IconButton(onClick = { }, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        IconButton(onClick = { }, modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 24.sp,
            fontFamily = FontFamily.SansSerif,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier.align(Alignment.Center)
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