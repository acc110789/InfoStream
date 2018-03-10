package com.info.app.net.zhihu_entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/10.
 */
@Entity(tableName = "zhihu_daily_news")
@TypeConverters(ZhihuTypeConverter::class)
class ZhihuDailyNewsQuestion {

    @ColumnInfo(name = "images")
    @Expose
    @SerializedName("images")
    var images: List<String>? = null

    @ColumnInfo(name = "type")
    @Expose
    @SerializedName("type")
    var type: Int = 0

    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    var id: Int = 0

    @ColumnInfo(name = "ga_prefix")
    @Expose
    @SerializedName("ga_prefix")
    var gaPrefix: String? = null

    @ColumnInfo(name = "title")
    @Expose
    @SerializedName("title")
    var title: String? = null

    @ColumnInfo(name = "favorite")
    @Expose
    var isFavorite: Boolean = false

    @ColumnInfo(name = "timestamp")
    @Expose
    var timestamp: Long = 0
}