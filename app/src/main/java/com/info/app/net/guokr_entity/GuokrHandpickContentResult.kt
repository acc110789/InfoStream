package com.info.app.net.guokr_entity

import android.arch.persistence.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/11.
 */
@Entity(tableName = "guokr_handpick_content")
@TypeConverters(GuokrContentTypeConverters::class)
class GuokrHandpickContentResult {

    @ColumnInfo(name = "image")
    @Expose
    @SerializedName("image")
    var image: String? = null

    @ColumnInfo(name = "is_replyable")
    @Expose
    @SerializedName("is_replyable")
    var isReplyable: Boolean = false

    @ColumnInfo(name = "channels")
    @Expose
    @SerializedName("channels")
    var channels: List<GuokrHandpickContentChannel>? = null

    @ColumnInfo(name = "channel_keys")
    @Expose
    @SerializedName("channel_keys")
    var channelKeys: List<String>? = null

    @ColumnInfo(name = "preface")
    @Expose
    @SerializedName("preface")
    var preface: String? = null

    @Embedded
    @Expose
    @SerializedName("subject")
    var subject: GuokrHandpickContentChannel? = null

    @ColumnInfo(name = "copyright")
    @Expose
    @SerializedName("copyright")
    var copyright: String? = null

    @Embedded
    @Expose
    @SerializedName("author")
    var author: GuokrHandpickNewsAuthor? = null

    @ColumnInfo(name = "image_description")
    @Expose
    @SerializedName("image_description")
    var imageDescription: String? = null

    @ColumnInfo(name = "content")
    @Expose
    @SerializedName("content")
    var content: String? = null

    @ColumnInfo(name = "is_show_summary")
    @Expose
    @SerializedName("is_show_summary")
    var isShowSummary: Boolean = false

    @ColumnInfo(name = "minisite_key")
    @Expose
    @SerializedName("minisite_key")
    var minisiteKey: String? = null

    @Embedded
    @Expose
    @SerializedName("image_info")
    var imageInfo: GuokrHandpickContentImageInfo? = null

    @ColumnInfo(name = "subject_key")
    @Expose
    @SerializedName("subject_key")
    var subjectKey: String? = null

    @Embedded
    @Expose
    @SerializedName("minisite")
    var minisite: GuokrHandpickContentMinisite? = null

    @ColumnInfo(name = "tags")
    @Expose
    @SerializedName("tags")
    var tags: List<String>? = null

    @ColumnInfo(name = "date_published")
    @Expose
    @SerializedName("date_published")
    var datePublished: String? = null

    @ColumnInfo(name = "replies_count")
    @Expose
    @SerializedName("replies_count")
    var repliesCount: Int = 0

    @ColumnInfo(name = "is_author_external")
    @Expose
    @SerializedName("is_author_external")
    var isAuthorExternal: Boolean = false

    @ColumnInfo(name = "recommends_count")
    @Expose
    @SerializedName("recommends_count")
    var recommendsCount: Int = 0

    @ColumnInfo(name = "title_hide")
    @Expose
    @SerializedName("title_hide")
    var titleHide: String? = null

    @ColumnInfo(name = "date_modified")
    @Expose
    @SerializedName("date_modified")
    var dateModified: String? = null

    @ColumnInfo(name = "url")
    @Expose
    @SerializedName("url")
    var url: String? = null

    @ColumnInfo(name = "title")
    @Expose
    @SerializedName("title")
    var title: String? = null

    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    var id: Int = 0

    @ColumnInfo(name = "small_image")
    @Expose
    @SerializedName("small_image")
    var smallImage: String? = null

    @ColumnInfo(name = "summary")
    @Expose
    @SerializedName("summary")
    var summary: String? = null

    @ColumnInfo(name = "ukey_author")
    @Expose
    @SerializedName("ukey_author")
    var ukeyAuthor: String? = null

    @ColumnInfo(name = "date_created")
    @Expose
    @SerializedName("date_created")
    var dateCreated: String? = null

    @ColumnInfo(name = "resource_url")
    @Expose
    @SerializedName("resource_url")
    var resourceUrl: String? = null

}