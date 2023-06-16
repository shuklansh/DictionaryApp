package com.shuklansh.dictionaryapp.feature_dictionary.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shuklansh.dictionaryapp.core.util.Resource
import com.shuklansh.dictionaryapp.feature_dictionary.domain.use_case.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo // the use case is called here
) : ViewModel() {

    // search box textfield state
    private val _searchQuery = mutableStateOf<String>("")
    val searchQuery = _searchQuery

    // wordinfostate
    private val _state = MutableStateFlow(WordInfoState())
    val state = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob : Job? = null

    fun onSearch(word : String){
        _searchQuery.value = word
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWordInfo(word).onEach {result->
                when(result){
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(UiEvent.showSnackBar(result.message ?: "Unknown error "))
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = true
                        )


                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }.launchIn(this)
        }
    }


}