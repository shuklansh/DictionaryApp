package com.shuklansh.dictionaryapp.feature_translate_de.domain.repository

import com.shuklansh.dictionaryapp.core.util.Resource
import com.shuklansh.dictionaryapp.feature_dictionary.domain.model.WordInfo
import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.DeTranslate
import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.Def
import kotlinx.coroutines.flow.Flow

interface TranslateRepository {

    fun getGermanTranslation(word : String) : Flow<Resource<List<Def>>>

}