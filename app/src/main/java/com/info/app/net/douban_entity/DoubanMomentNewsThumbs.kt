package com.info.app.net.douban_entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/11.
 */


class DoubanMomentNewsThumbs {

    @Embedded
    @Expose
    @SerializedName("medium")
    var medium: DoubanMomentNewsMedium? = null

    @ColumnInfo(name = "thumb_description")
    @Expose
    @SerializedName("description")
    var description: String? = null

    @Embedded
    @Expose
    @SerializedName("large")
    var large: DoubanMomentNewsLarge? = null

    @Expose
    @SerializedName("tag_name")
    var tagName: String? = null

    @Embedded
    @Expose
    @SerializedName("small")
    var small: DoubanMomentNewsSmall? = null

    @ColumnInfo(name = "thumb_id")
    @Expose
    @SerializedName("id")
    var id: Int = 0

}