package com.shuklansh.dictionaryapp.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.shuklansh.dictionaryapp.feature_dictionary.data.local.entity.WordInfoEntity
import com.shuklansh.dictionaryapp.feature_dictionary.domain.model.WordInfo
import org.intellij.lang.annotations.Language


@Dao
interface WordInfoDao {


    @Upsert
    suspend fun insertWordInfo( infos : List<WordInfoEntity>)

    @Query("DELETE FROM wordinfoentity WHERE word IN (:words)")
    suspend fun deleteWordInfos(words : List<String>)

    @Query("SELECT * FROM wordinfoentity WHERE word LIKE  '%' || :word || '%' ")
    suspend fun getWordInfos(word : String) : List<WordInfoEntity>
}