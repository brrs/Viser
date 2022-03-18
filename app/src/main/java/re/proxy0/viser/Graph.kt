package re.proxy0.viser

import android.content.Context
import re.proxy0.viser.data.UIPreferences

object Graph {
    lateinit var uiPreferences: UIPreferences

    fun provide(context: Context) {
        uiPreferences = UIPreferences(context)
    }
}