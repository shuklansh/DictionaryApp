package com.shuklansh.dictionaryapp.feature_translate_de.data.remote.Dto


import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.Mean

data class MeanDto(
    val text: String
) {
    fun toMean() : Mean{
        return Mean(
            text = text
        )
    }
}