package com.shuklansh.dictionaryapp.feature_translate_de.domain.use_case

import com.shuklansh.dictionaryapp.core.util.Resource
import com.shuklansh.dictionaryapp.feature_dictionary.domain.model.WordInfo
import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.DeTranslate
import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.Def
import com.shuklansh.dictionaryapp.feature_translate_de.domain.repository.TranslateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTransledWord(
    val repository : TranslateRepository
) {

    operator fun invoke(word : String) : Flow<Resource<List<Def>>> {
        if(word.isBlank()){
            return flow{ }
        }
        return repository.getGermanTranslation(word)
    }

}