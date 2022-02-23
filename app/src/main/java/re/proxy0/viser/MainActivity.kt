package re.proxy0.viser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import re.proxy0.viser.ui.screens.BookDetailsScreen
import re.proxy0.viser.ui.screens.LibraryScreen
import re.proxy0.viser.ui.screens.ReaderScreen
import re.proxy0.viser.ui.screens.ScreenList
import re.proxy0.viser.ui.theme.ViserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViserTheme {
                ProvideWindowInsets {
                    val navController = rememberNavController()
                    NavHost(navController = navController, ScreenList.Library.route) {
                        composable(ScreenList.Reader.route) { ReaderScreen(navController) }
                        composable(ScreenList.Library.route) { LibraryScreen(navController) }
                        composable(ScreenList.BookDetails.route) { BookDetailsScreen(navController) }
                    }
                }
            }
        }
    }
}
