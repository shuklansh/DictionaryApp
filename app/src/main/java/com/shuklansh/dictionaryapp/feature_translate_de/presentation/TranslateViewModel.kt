package com.shuklansh.dictionaryapp.feature_translate_de.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shuklansh.dictionaryapp.core.util.Resource
import com.shuklansh.dictionaryapp.feature_dictionary.presentation.UiEvent
import com.shuklansh.dictionaryapp.feature_translate_de.domain.use_case.GetTransledWord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslateViewModel @Inject constructor(
    val getTranslationWordUseCase : GetTransledWord
) : ViewModel() {

    private val _queryTranslate = MutableStateFlow(queryState())
    val query = _queryTranslate.asStateFlow()

    fun updateTranslationQuery(word: String) {
        _queryTranslate.update {
            it.copy(
                queryWord = word
            )
        }
    }

    private val _state = MutableStateFlow(WordTranslateState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun getTranslation(word : String){
        updateTranslationQuery(word)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getTranslationWordUseCase(word).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            translatedWordItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(UiEvent.showSnackBar(result.message ?: "Unknown error "))
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            translatedWordItems = result.data ?: emptyList(),
                            isLoading = true
                        )


                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            translatedWordItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }.launchIn(this)
        }
    }

}