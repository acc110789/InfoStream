package com.info.app.net.zhihu_entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/10.
 */
class ZhihuDailyNews {
    @Expose
    @SerializedName("date")
    private var date: String? = null

    @Expose
    @SerializedName("stories")
    private var stories: List<ZhihuDailyNewsQuestion>? = null

    fun getDate(): String? {
        return date
    }

    fun setDate(date: String) {
        this.date = date
    }

    fun getStories(): List<ZhihuDailyNewsQuestion>? {
        return stories
    }

    fun setStories(stories: List<ZhihuDailyNewsQuestion>) {
        this.stories = stories
    }
}