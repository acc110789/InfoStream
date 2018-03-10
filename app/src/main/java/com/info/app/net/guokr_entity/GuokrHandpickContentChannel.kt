package com.info.app.net.guokr_entity

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/11.
 */
class GuokrHandpickContentChannel {

    @ColumnInfo(name = "channel_url")
    @Expose
    @SerializedName("url")
    var url: String? = null

    @ColumnInfo(name = "channel_date_created")
    @Expose
    @SerializedName("date_created")
    var dateCreated: String? = null

    @ColumnInfo(name = "channel_name")
    @Expose
    @SerializedName("name")
    var name: String? = null

    @ColumnInfo(name = "channel_key")
    @Expose
    @SerializedName("key")
    var key: String? = null

    @ColumnInfo(name = "channel_articles_count")
    @Expose
    @SerializedName("articles_count")
    var articlesCount: Int = 0
}