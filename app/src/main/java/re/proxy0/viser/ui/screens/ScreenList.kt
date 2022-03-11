package re.proxy0.viser.ui.screens

sealed class ScreenList(val route: String) {
    object Library : ScreenList(route = "library")
    object Reader : ScreenList(route = "reader")
    object BookDetails : ScreenList(route = "bookDetails")
    object Settings : ScreenList(route = "settings")
    object Splash : ScreenList(route = "splash")
}
