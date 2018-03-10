package com.info.app.net.guokr_entity

import android.arch.persistence.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/11.
 */
class GuokrHandpickContent {

    @Expose
    @SerializedName("now")
    var now: String? = null

    @Expose
    @SerializedName("ok")
    var isOk: Boolean = false

    @Embedded
    @Expose
    @SerializedName("result")
    var result: GuokrHandpickContentResult? = null

}