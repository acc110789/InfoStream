package com.info.app.net.guokr_entity

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/11.
 */
class GuokrHandpickContentMinisite {

    @ColumnInfo(name = "minisite_name")
    @Expose
    @SerializedName("name")
    var name: String? = null

    @ColumnInfo(name = "minisite_url")
    @Expose
    @SerializedName("url")
    var url: String? = null

    @ColumnInfo(name = "minisite_introduction")
    @Expose
    @SerializedName("introduction")
    var introduction: String? = null

    @ColumnInfo(name = "minisite__key")
    @Expose
    @SerializedName("key")
    var key: String? = null

    @ColumnInfo(name = "minisite_date_created")
    @Expose
    @SerializedName("date_created")
    var dateCreated: String? = null

    @ColumnInfo(name = "minisite_icon")
    @Expose
    @SerializedName("icon")
    var icon: String? = null
}