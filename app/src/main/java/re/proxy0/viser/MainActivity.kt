package re.proxy0.viser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import re.proxy0.viser.ui.screens.*
import re.proxy0.viser.ui.theme.ViserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViserTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, ScreenList.Splash.route) {
                    composable(ScreenList.Reader.route) { ReaderScreen(navController) }
                    composable(ScreenList.Library.route) { LibraryScreen(navController) }
                    composable(ScreenList.BookDetails.route) { BookDetailsScreen(navController) }
                    composable(ScreenList.Settings.route) { SettingsScreen(navController) }
                    composable(ScreenList.Splash.route) { SplashScreen(navController) }
                }
            }
        }
    }
}