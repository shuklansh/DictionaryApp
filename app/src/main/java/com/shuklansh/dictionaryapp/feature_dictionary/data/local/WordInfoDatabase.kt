package com.shuklansh.dictionaryapp.feature_dictionary.data.local

import androidx.room.*
import com.shuklansh.dictionaryapp.feature_dictionary.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase : RoomDatabase() {

    abstract val dao : WordInfoDao
}