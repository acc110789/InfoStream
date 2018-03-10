package com.info.app.net.douban_entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/11.
 */
class DoubanMomentNews {

    @Expose
    @SerializedName("count")
    var count: Int = 0

    @Expose
    @SerializedName("posts")
    var posts: List<DoubanMomentNewsPosts>? = null

    @Expose
    @SerializedName("offset")
    var offset: Int = 0

    @Expose
    @SerializedName("date")
    var date: String? = null

    @Expose
    @SerializedName("total")
    var total: Int = 0

}
