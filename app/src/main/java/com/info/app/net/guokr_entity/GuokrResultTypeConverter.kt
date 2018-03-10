package com.info.app.net.guokr_entity

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

/**
 * Created by zhangxiaolong on 18/3/11.
 */
object GuokrResultTypeConverter {

    @TypeConverter
    fun stringListToString(strings: List<String>): String {
        return Gson().toJson(strings)
    }

    @TypeConverter
    fun stringToStringList(string: String): List<String>? {
        val listType = object : TypeToken<ArrayList<String>>() {

        }.type
        return Gson().fromJson<List<String>>(string, listType)
    }

    @TypeConverter
    fun resultListToString(channels: List<GuokrHandpickContentChannel>): String {
        return Gson().toJson(channels)
    }

    @TypeConverter
    fun stringToResultList(string: String): List<GuokrHandpickContentChannel>? {
        val listType = object : TypeToken<ArrayList<GuokrHandpickContentChannel>>() {

        }.type
        return Gson().fromJson<List<GuokrHandpickContentChannel>>(string, listType)
    }

    @TypeConverter
    fun authorListToString(authors: List<GuokrHandpickNewsAuthor>): String {
        return Gson().toJson(authors)
    }

    @TypeConverter
    fun stringToAuthorList(string: String): List<GuokrHandpickNewsAuthor>? {
        val listType = object : TypeToken<ArrayList<GuokrHandpickNewsAuthor>>() {

        }.type
        return Gson().fromJson<List<GuokrHandpickNewsAuthor>>(string, listType)
    }

}