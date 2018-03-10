package com.info.app.net.guokr_entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/11.
 */
class GuokrHandpickNews {

    @Expose
    @SerializedName("now")
    var now: String? = null

    @Expose
    @SerializedName("ok")
    var isOk: Boolean = false

    @Expose
    @SerializedName("limit")
    var limit: Int = 0

    @Expose
    @SerializedName("result")
    var result: List<GuokrHandpickNewsResult>? = null

    @Expose
    @SerializedName("offset")
    var offset: Int = 0

    @Expose
    @SerializedName("total")
    var total: Int = 0

}