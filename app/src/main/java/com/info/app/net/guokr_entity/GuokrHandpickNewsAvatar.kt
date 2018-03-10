package com.info.app.net.guokr_entity

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/11.
 */


class GuokrHandpickNewsAvatar {

    @ColumnInfo(name = "avatar_large")
    @Expose
    @SerializedName("large")
    var large: String? = null

    @ColumnInfo(name = "avatar_small")
    @Expose
    @SerializedName("small")
    var small: String? = null

    @ColumnInfo(name = "avatar_normal")
    @Expose
    @SerializedName("normal")
    var normal: String? = null
}