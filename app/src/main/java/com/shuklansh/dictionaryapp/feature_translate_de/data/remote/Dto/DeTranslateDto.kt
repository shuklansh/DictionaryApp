package com.shuklansh.dictionaryapp.feature_translate_de.data.remote.Dto

import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.DeTranslate
import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.Def

data class DeTranslateDto(
    val def: List<DefDto>,
    val head: HeadDto
){
    fun toDeTranslate(  ) : DeTranslate {
        return DeTranslate(
            def = def.map { it.toDef() }
        )
    }
}