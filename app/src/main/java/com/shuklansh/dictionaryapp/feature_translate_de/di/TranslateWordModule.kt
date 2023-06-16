package com.shuklansh.dictionaryapp.feature_translate_de.di

import com.shuklansh.dictionaryapp.feature_translate_de.data.remote.TranslateApi
import com.shuklansh.dictionaryapp.feature_translate_de.data.repository.TranslateRepositoryImpl
import com.shuklansh.dictionaryapp.feature_translate_de.domain.repository.TranslateRepository
import com.shuklansh.dictionaryapp.feature_translate_de.domain.use_case.GetTransledWord
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TranslateWordModule {

    @Provides
    @Singleton
    fun getTranslateUseCase(
        repository : TranslateRepository
    ) : GetTransledWord {
        return GetTransledWord(repository)
    }


    @Provides
    @Singleton
    fun getTranslateRepository(
        api: TranslateApi
    ) : TranslateRepository {
        return TranslateRepositoryImpl(
            api = api
        )
    }


    @Provides
    @Singleton
    fun provideRetrofitApi(): TranslateApi {
        return Retrofit.Builder()
            .baseUrl(TranslateApi.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TranslateApi::class.java)
    }

}