package com.shuklansh.dictionaryapp.feature_dictionary.domain.use_case

import com.shuklansh.dictionaryapp.core.util.Resource
import com.shuklansh.dictionaryapp.feature_dictionary.domain.model.WordInfo
import com.shuklansh.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


// these use cases will be called from viewmodel (viewmodels belong in presentation layer)
class GetWordInfo(
    private val repository: WordInfoRepository
) {
    // use invoke to call the usecase like a function even if it was a class
    operator fun invoke(word : String) : Flow<Resource<List<WordInfo>>> {
        if(word.isBlank()){
            return flow{ }
        }
        return repository.getWordInfo(word)
    }

}