package com.info.app.net.douban_entity

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/11.
 */


class DoubanMomentNewsSmall {

    @ColumnInfo(name = "small_url")
    @Expose
    @SerializedName("url")
    var url: String? = null

    @ColumnInfo(name = "small_width")
    @Expose
    @SerializedName("width")
    var width: Int = 0

    @ColumnInfo(name = "small_height")
    @Expose
    @SerializedName("height")
    var height: Int = 0

}