package re.proxy0.viser.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class UIPreferences(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("ui_preferences")
        val DARK_MODE = booleanPreferencesKey("dark_mode")
        val READER_TEXT_SIZE = intPreferencesKey("reader_text_size")
        val READER_TEXT_LINE_SPACING = floatPreferencesKey("reader_text_line_spacing")
    }

    val darkMode: Flow<Boolean> = context.dataStore.data.map { it[DARK_MODE] ?: false }

    suspend fun saveDarkMode(darkMode: Boolean) {
        context.dataStore.edit { it[DARK_MODE] = darkMode }
    }

    val readerTextSize: Flow<Int> = context.dataStore.data.map { it[READER_TEXT_SIZE] ?: 16 }

    suspend fun saveReaderTextSize(fontSize: Int) {
        context.dataStore.edit { it[READER_TEXT_SIZE] = fontSize }
    }

    val readerTextSpacing: Flow<Float> =
        context.dataStore.data.map { it[READER_TEXT_LINE_SPACING] ?: 1f }

    suspend fun saveReaderTextSpacing(spacing: Float) {
        context.dataStore.edit { it[READER_TEXT_LINE_SPACING] = spacing }
    }
}