package com.shuklansh.dictionaryapp.feature_translate_de.data.remote.Dto

import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.Syn

data class SynDto(
    val fr: Int,
    val gen: String,
    val pos: String,
    val text: String
) {
    fun toSyn() : Syn{
        return Syn(
            fr = fr,
            gen = gen,
            pos = pos,
            text  =text
        )
    }
}