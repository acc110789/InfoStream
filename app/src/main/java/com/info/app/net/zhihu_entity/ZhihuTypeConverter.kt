package com.info.app.net.zhihu_entity

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

/**
 * Created by zhangxiaolong on 18/3/11.
 */
object ZhihuTypeConverter {

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

}