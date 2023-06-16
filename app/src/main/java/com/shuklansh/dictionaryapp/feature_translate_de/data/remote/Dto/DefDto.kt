package com.shuklansh.dictionaryapp.feature_translate_de.data.remote.Dto

import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.Def

data class DefDto(
    val pos: String,
    val text: String,
    val tr: List<TrDto>,
    val ts: String
)
{
    fun toDef() : Def {
        return Def(
            pos= pos,
            text = text,
            tr = tr.map { it.toTr() },
            ts = ts
        )
    }

}