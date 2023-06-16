package com.shuklansh.dictionaryapp.feature_translate_de.domain.model

import com.shuklansh.dictionaryapp.feature_translate_de.data.remote.Dto.TrDto

data class Def(
    val pos: String,
    val text: String,
    val tr: List<Tr>,
    val ts: String
)
