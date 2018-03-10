package com.info.app.net.douban_entity

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/11.
 */


class DoubanMomentNewsLarge {

    @ColumnInfo(name = "large_url")
    @Expose
    @SerializedName("url")
    var url: String? = null

    @ColumnInfo(name = "large_width")
    @Expose
    @SerializedName("width")
    var width: Int = 0

    @ColumnInfo(name = "large_height")
    @Expose
    @SerializedName("height")
    var height: Int = 0

}