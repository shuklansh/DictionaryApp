package com.shuklansh.dictionaryapp.feature_dictionary.data.remote.dto

import com.shuklansh.dictionaryapp.feature_dictionary.data.local.entity.WordInfoEntity
import com.shuklansh.dictionaryapp.feature_dictionary.domain.model.WordInfo

data class WordInfoDto(
    val license: LicenseDto,
    val meanings: List<MeaningDto>,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
) {
    fun toWordInfoEntity() : WordInfoEntity{
        return WordInfoEntity(
            word = word,
            meanings = meanings.map { it.toMeaning() },
        )
    }


}