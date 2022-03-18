package re.proxy0.viser.ui.screens.reader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import re.proxy0.viser.Graph
import re.proxy0.viser.data.UIPreferences
import re.proxy0.viser.data.generateBook


class ReaderViewModel(
    private val uiPreferences: UIPreferences = Graph.uiPreferences
) : ViewModel() {
    private val fontSize = MutableStateFlow(16)
    private val lineSpacing = MutableStateFlow(1.25f)
    private val book = MutableStateFlow(generateBook())
    private val text = MutableStateFlow(book.value.chapters.first().text)
    private val title = MutableStateFlow(book.value.chapters.first().name)

    private val _state = MutableStateFlow(ReaderViewState())
    val state: StateFlow<ReaderViewState>
        get() = _state

    init {
        viewModelScope.launch {
            combine(
                text,
                title,
                uiPreferences.readerTextSize,
                uiPreferences.readerTextSpacing
            ) { text, title, fontSize, lineSpacing ->
                ReaderViewState(
                    text = text,
                    title = title,
                    fontSize = fontSize,
                    lineSpacing = lineSpacing
                )
            }.collect { _state.value = it }
        }
    }

    fun saveFontSize(size: Int) {
        viewModelScope.launch {
            fontSize.value = size
            uiPreferences.saveReaderTextSize(size)
        }
    }

    fun saveLineSpacing(spacing: Float) {
        viewModelScope.launch {
            lineSpacing.value = spacing
            uiPreferences.saveReaderTextSpacing(spacing)
        }
    }
}

data class ReaderViewState(
    val text: String = "",
    val title: String = "",
    val fontSize: Int = 16,
    val lineSpacing: Float = 1f
)