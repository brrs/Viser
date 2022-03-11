package re.proxy0.viser.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import re.proxy0.viser.R
import re.proxy0.viser.data.Book
import re.proxy0.viser.data.generateBook
import re.proxy0.viser.ui.theme.BookStatusCompleted
import re.proxy0.viser.ui.theme.BookStatusOngoing

@Composable
fun BookDetailsScreen(navController: NavController) {
    Column(
        Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    ) {
        val book = remember { generateBook() }
        Header(navController, book.name)
        Column {
            MainInformation(book, navController)
        }
    }
}

@Composable
fun Header(navController: NavController, bookName: String) {
    Box(
        Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = { navController.navigateUp() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary
            )
        }
        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = { }
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary
            )
        }
        Text(
            text = bookName,
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

@Composable
fun MainInformation(book: Book, navController: NavController) {
    Surface(
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            Modifier.verticalScroll(rememberScrollState())
        ) {
            Row(Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
                Surface(color = Color.LightGray, shape = RoundedCornerShape(8.dp)) {
                    Image(
                        painter = rememberImagePainter(book.coverUrl),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(156.dp)
                            .height(240.dp)
                    )
                }
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(
                        text = book.name,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onPrimary,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = book.status.toString().lowercase()
                            .replaceFirstChar { it.uppercase() },
                        color = if (book.status == Book.Status.COMPLETED) BookStatusCompleted else BookStatusOngoing,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = book.author,
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 16.sp
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedButton(
                        onClick = { navController.navigate(ScreenList.Reader.route) },
                        modifier = Modifier.padding(8.dp),
                        border = BorderStroke(0.5.dp, MaterialTheme.colors.secondary),
                        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = MaterialTheme.colors.background)

                    ) {
                        Text(
                            text = "Start reading",
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.button,
                            fontSize = 16.sp
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            LazyRow(Modifier.fillMaxWidth()) {
                items(book.genres) { genre ->
                    GenreItem(
                        name = genre.lowercase().replaceFirstChar { it.uppercase() },
                        onGenreClick = { }
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            Column(Modifier.padding(horizontal = 16.dp)) {
                Divider(Modifier.fillMaxWidth())
                Spacer(Modifier.height(24.dp))
                Column {
                    Text(
                        text = "Description:",
                        fontSize = 18.sp,
                        color = MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.caption
                    )
                    Spacer(Modifier.height(8.dp))
                    Column(Modifier.padding(8.dp)) {
                        var seeMore by remember { mutableStateOf(true) }
                        Text(
                            text = book.description,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onPrimary,
                            maxLines = if (seeMore) 5 else Int.MAX_VALUE,
                            modifier = Modifier.animateContentSize()
                        )
                        val textButton = if (seeMore) "see more" else "see less"
                        TextButton(
                            onClick = { seeMore = !seeMore },
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = textButton,
                                style = MaterialTheme.typography.button,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onPrimary,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GenreItem(name: String, onGenreClick: () -> Unit) {
    TextButton(
        onClick = onGenreClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
        modifier = Modifier.padding(horizontal = 16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        Text(
            fontSize = 14.sp,
            text = name,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
fun ChaptersList(navController: NavController, book: Book) {
    Column {
        Text(
            text = "${book.chapters.size} chapters",
            fontSize = 18.sp,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(16.dp)
        )
        /*Column {
            book.chapters.forEach { chapter ->
                Chapter(name = chapter.name, date = chapter.date)
            }
        }*/
        LazyColumn {
            items(book.chapters) { chapter ->
                Chapter(name = chapter.name, date = chapter.date)
            }
        }
    }
}

@Composable
fun Chapter(name: String, date: String) {
    Surface(
        //color = Color.Transparent,
        modifier = Modifier.clickable { }
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                Modifier
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onPrimary,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = date,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onPrimary,
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_download),
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}
