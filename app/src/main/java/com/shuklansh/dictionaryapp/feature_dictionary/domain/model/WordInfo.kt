package com.shuklansh.dictionaryapp.feature_dictionary.domain.model

import com.shuklansh.dictionaryapp.feature_dictionary.data.remote.dto.LicenseDto
import com.shuklansh.dictionaryapp.feature_dictionary.data.remote.dto.MeaningDto
import com.shuklansh.dictionaryapp.feature_dictionary.data.remote.dto.PhoneticDto

data class WordInfo(
    val meanings: List<Meaning>,
    val word: String
)