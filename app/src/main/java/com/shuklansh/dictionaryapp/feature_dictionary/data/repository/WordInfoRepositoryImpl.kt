package com.shuklansh.dictionaryapp.feature_dictionary.data.repository

import com.shuklansh.dictionaryapp.core.util.Resource
import com.shuklansh.dictionaryapp.feature_dictionary.data.local.WordInfoDao
import com.shuklansh.dictionaryapp.feature_dictionary.data.remote.dictionary_api
import com.shuklansh.dictionaryapp.feature_dictionary.domain.model.WordInfo
import com.shuklansh.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpRetryException

// this is where caching logic belongs.
class WordInfoRepositoryImpl(
    private val api : dictionary_api,
    private val dao : WordInfoDao
) : WordInfoRepository {

    // get data from api -> insert to db -> show in ui (single source of truth)
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())


        // job of repository is also to map data object to domain level objs.(data level -> (wordinfoentity), domain level -> wordinfo)
        val wordinfos = dao.getWordInfos(word).map { it.toWordInfo() } // wordinfos is cached wordinfos

        emit(Resource.Loading(data = wordinfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            // now if success we have updated word infos -> so we will first delete old data of same word if exists
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            // -> update the new data from api to local db
            dao.insertWordInfo(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e : HttpException){ // if we get bad response
            emit(Resource.Error(message = "Something went wrong", data = wordinfos ))
        } catch (e : IOException){ // if we dont have internet connection/wrong parsing
            emit(Resource.Error(message = "Check Your Internet Connection", data = wordinfos ))
        }

        // -> then show that data to user
        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(data = newWordInfos))


    }
}
