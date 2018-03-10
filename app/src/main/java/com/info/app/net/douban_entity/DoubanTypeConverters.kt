package com.info.app.net.douban_entity

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

/**
 * Created by zhangxiaolong on 18/3/11.
 */
object DoubanTypeConverters {

    @TypeConverter
    fun thumbListToString(thumbs: List<DoubanMomentNewsThumbs>): String {
        return Gson().toJson(thumbs)
    }

    @TypeConverter
    fun stringToThumbList(string: String): List<DoubanMomentNewsThumbs>? {
        val listType = object : TypeToken<ArrayList<DoubanMomentNewsThumbs>>() {

        }.type
        return Gson().fromJson<List<DoubanMomentNewsThumbs>>(string, listType)
    }

}