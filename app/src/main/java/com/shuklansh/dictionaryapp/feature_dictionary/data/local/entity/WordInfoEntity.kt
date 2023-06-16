package com.shuklansh.dictionaryapp.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shuklansh.dictionaryapp.feature_dictionary.domain.model.Meaning
import com.shuklansh.dictionaryapp.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val meanings: List<Meaning>,
    val word: String
){
    fun toWordInfo() : WordInfo{
        return WordInfo(
            meanings = meanings,
            word = word
        )
    }
}