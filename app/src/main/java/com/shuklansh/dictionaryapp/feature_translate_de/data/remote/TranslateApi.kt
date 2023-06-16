package com.shuklansh.dictionaryapp.feature_translate_de.data.remote

import com.shuklansh.dictionaryapp.BuildConfig
import com.shuklansh.dictionaryapp.feature_translate_de.data.remote.Dto.DeTranslateDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslateApi {

    @GET("api/v1/dicservice.json/lookup")
    suspend fun getWordMeaning(
        @Query("key") key : String = BuildConfig.TRANSLATE_KEY  ,
        @Query("lang") lang : String = "en-de",
        @Query("text") text : String
    ) : DeTranslateDto

    companion object{
        const val URL = "https://dictionary.yandex.net/"
    }

}