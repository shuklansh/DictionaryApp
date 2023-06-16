package com.shuklansh.dictionaryapp.feature_translate_de.data.remote.Dto

import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.Tr

data class TrDto(
    val fr: Int?,
    val gen: String?,
    val mean: List<MeanDto>?,
    val pos: String?,
    val syn: List<SynDto>?,
    val text: String?
) {
    fun toTr() : Tr {
        return Tr(
            fr = fr,
            gen = gen,
            mean = mean?.map { it.toMean() },
            pos = pos,
            //syn = syn.map { it.toSyn() },
            text=text
        )
    }
}