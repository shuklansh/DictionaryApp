package com.shuklansh.dictionaryapp.feature_dictionary.presentation

import com.shuklansh.dictionaryapp.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems : List<WordInfo> = emptyList(),
    val isLoading : Boolean = false
)

data class queryState(
    val queryword : String = ""
)