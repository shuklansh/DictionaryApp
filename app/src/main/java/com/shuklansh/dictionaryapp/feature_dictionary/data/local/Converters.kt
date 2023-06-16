package com.shuklansh.dictionaryapp.feature_dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.reflect.TypeToken
import com.shuklansh.dictionaryapp.feature_dictionary.data.util.JsonParser
import com.shuklansh.dictionaryapp.feature_dictionary.domain.model.Meaning

// here we specify the type of converter (gson/moshi etc)
@ProvidedTypeConverter //this annotation used because by default typeconverters cant have constructor args
class Converters(
    private val jsonParser: JsonParser
) {

    // function to take a string and convert it to list of meanings
    @TypeConverter
    fun fromMeaningsJson(json : String) : List<Meaning>{
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json = json,
            type = object : TypeToken<ArrayList<Meaning>>(){}.type) ?: emptyList()
    }

    // function to take a list of meanings and convert it to string as json to be stored in entity
    @TypeConverter
    fun toMeaningsJson(meanings : List<Meaning>) : String{
        return jsonParser.toJson(
            obj = meanings,
            type = object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"
    }

}