package com.shuklansh.dictionaryapp.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.shuklansh.dictionaryapp.feature_dictionary.data.local.Converters
import com.shuklansh.dictionaryapp.feature_dictionary.data.local.WordInfoDao
import com.shuklansh.dictionaryapp.feature_dictionary.data.local.WordInfoDatabase
import com.shuklansh.dictionaryapp.feature_dictionary.data.remote.dictionary_api
import com.shuklansh.dictionaryapp.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.shuklansh.dictionaryapp.feature_dictionary.data.util.GsonParser
import com.shuklansh.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import com.shuklansh.dictionaryapp.feature_dictionary.domain.use_case.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(
        repository: WordInfoRepository
    ): GetWordInfo {
        return GetWordInfo(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        api: dictionary_api,
        db: WordInfoDatabase
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(
            api, db.dao
        )
    }

    @Provides
    @Singleton
    fun providesWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "WordInfoDb"
        ).addTypeConverter(Converters(GsonParser(Gson()))).build()
    }

    @Provides
    @Singleton
    fun providesApiRetrofit(): dictionary_api {
        return Retrofit.Builder()
            .baseUrl(dictionary_api.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(dictionary_api::class.java)
    }

}