package com.shuklansh.dictionaryapp.feature_dictionary.data.remote.dto

import com.shuklansh.dictionaryapp.feature_dictionary.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
) {
    //mapper function to get only the data we want to show
    // called in domain-> model
    fun toDefinition() : Definition{
        return Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }


}