package com.shuklansh.dictionaryapp.feature_translate_de.data.repository

import com.shuklansh.dictionaryapp.BuildConfig
import com.shuklansh.dictionaryapp.core.util.Resource
import com.shuklansh.dictionaryapp.feature_translate_de.data.remote.TranslateApi
import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.DeTranslate
import com.shuklansh.dictionaryapp.feature_translate_de.domain.model.Def
import com.shuklansh.dictionaryapp.feature_translate_de.domain.repository.TranslateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class TranslateRepositoryImpl(
    val api : TranslateApi
) : TranslateRepository {
    override fun getGermanTranslation(word: String): Flow<Resource<List<Def>>> = flow{

        emit(Resource.Loading())

        try {
            val translationOutput = api.getWordMeaning(text = word)

            emit(Resource.Success(data = translationOutput.def.map { it.toDef() } ))

        } catch (e : HttpException){

            emit(Resource.Error(message = "Could not find translation for $word"))

        } catch (e : IOException){
            emit(Resource.Error(message = "Check Your Network Connection"))
        }

    }


}