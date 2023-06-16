package com.shuklansh.dictionaryapp.feature_translate_de.domain.model

import com.shuklansh.dictionaryapp.feature_translate_de.data.remote.Dto.MeanDto
import com.shuklansh.dictionaryapp.feature_translate_de.data.remote.Dto.SynDto

data class Tr(
    val fr: Int?,
    val gen: String?,
    val mean: List<Mean>?,
    val pos: String?,
    //val syn: List<Syn>,
    val text: String?
)
