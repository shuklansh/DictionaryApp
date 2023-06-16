package com.shuklansh.dictionaryapp.feature_translate_de.presentation

import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.DeTranslate
import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.Def

data class WordTranslateState(
    val translatedWordItems : List<Def> = emptyList(),
    val isLoading : Boolean = false
)

data class queryState(
    val queryWord : String = ""
)
