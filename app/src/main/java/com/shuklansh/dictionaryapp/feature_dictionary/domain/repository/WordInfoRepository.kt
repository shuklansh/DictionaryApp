package com.shuklansh.dictionaryapp.feature_dictionary.domain.repository

import com.shuklansh.dictionaryapp.core.util.Resource
import com.shuklansh.dictionaryapp.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow


//this is just the declaration, implementation is in data layer
interface WordInfoRepository {

    fun getWordInfo(word : String) : Flow<Resource<List<WordInfo>>>

}