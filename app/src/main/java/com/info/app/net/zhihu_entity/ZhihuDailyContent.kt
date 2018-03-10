package com.info.app.net.zhihu_entity

import android.arch.persistence.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/11.
 */
@Entity(tableName = "zhihu_daily_content")
@TypeConverters(ZhihuTypeConverter::class)
class ZhihuDailyContent {

    @ColumnInfo(name = "body")
    @Expose
    @SerializedName("body")
    var body: String? = null

    @ColumnInfo(name = "image_source")
    @Expose
    @SerializedName("image_source")
    var imageSource: String? = null

    @ColumnInfo(name = "title")
    @Expose
    @SerializedName("title")
    var title: String? = null

    @ColumnInfo(name = "image")
    @Expose
    @SerializedName("image")
    var image: String? = null

    @ColumnInfo(name = "share_url")
    @Expose
    @SerializedName("share_url")
    var shareUrl: String? = null

    @ColumnInfo(name = "js")
    @Expose
    @SerializedName("js")
    var js: List<String>? = null

    @Ignore // This field will be ignored.
    @Expose
    @SerializedName("ga_prefix")
    var gaPrefix: String? = null

    @ColumnInfo(name = "images")
    @Expose
    @SerializedName("images")
    var images: List<String>? = null

    @ColumnInfo(name = "type")
    @Expose
    @SerializedName("type")
    var type: Int = 0

    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    var id: Int = 0

    @ColumnInfo(name = "css")
    @Expose
    @SerializedName("css")
    var css: List<String>? = null

}