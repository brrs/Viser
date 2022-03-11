package re.proxy0.viser.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import re.proxy0.viser.data.Book
import re.proxy0.viser.data.BookCollection


@Composable
fun BookCollectionList(
    bookCollection: BookCollection,
    onBookClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .heightIn(min = 56.dp)
                .padding(start = 16.dp)
        ) {
            Text(
                text = bookCollection.name,
                color = MaterialTheme.colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            IconButton(
                onClick = { /* todo */ },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowForward,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = null
                )
            }
        }
        Snacks(bookCollection.books, onBookClick)
    }
}

@Composable
private fun Snacks(
    books: List<Book>,
    onBookClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
    ) {
        items(books) { book ->
            BookItem(book, onBookClick)
        }
    }
}

@Composable
fun BookItem(
    book: Book,
    onBookClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.padding(
            start = 8.dp,
            end = 8.dp,
            bottom = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = { onBookClick(0 /*TODO*/) })
        ) {
            BookImage(
                imageUrl = book.coverUrl,
                contentDescription = null,
                modifier = Modifier.size(140.dp)
            )
            Text(
                text = book.name,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(top = 8.dp, start = 12.dp)
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun BookImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.LightGray,
        modifier = modifier
    ) {
        Image(
            painter = rememberImagePainter(
                data = imageUrl,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}