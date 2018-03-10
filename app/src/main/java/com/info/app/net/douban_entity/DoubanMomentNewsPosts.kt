package com.info.app.net.douban_entity

import android.arch.persistence.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/11.
 */
@Entity(tableName = "douban_moment_news")
@TypeConverters(DoubanTypeConverters::class)
class DoubanMomentNewsPosts {

    @ColumnInfo(name = "display_style")
    @Expose
    @SerializedName("display_style")
    var displayStyle: Int = 0

    @ColumnInfo(name = "is_editor_choice")
    @Expose
    @SerializedName("is_editor_choice")
    var is_editor_choice: Boolean = false
        private set

    @ColumnInfo(name = "published_time")
    @Expose
    @SerializedName("published_time")
    var publishedTime: String? = null

    @ColumnInfo(name = "url")
    @Expose
    @SerializedName("url")
    var url: String? = null

    @ColumnInfo(name = "short_url")
    @Expose
    @SerializedName("short_url")
    var shortUrl: String? = null

    @ColumnInfo(name = "is_liked")
    @Expose
    @SerializedName("is_liked")
    var is_liked: Boolean = false
        private set

    @Embedded
    @Expose
    @SerializedName("author")
    var author: DoubanMomentNewsAuthor? = null

    @ColumnInfo(name = "column")
    @Expose
    @SerializedName("column")
    var column: String? = null

    @ColumnInfo(name = "app_css")
    @Expose
    @SerializedName("app_css")
    var appCss: Int = 0

    @ColumnInfo(name = "abstract")
    @Expose
    @SerializedName("abstract")
    var abs: String? = null

    @ColumnInfo(name = "date")
    @Expose
    @SerializedName("date")
    var date: String? = null

    @ColumnInfo(name = "like_count")
    @Expose
    @SerializedName("like_count")
    var likeCount: Int = 0

    @ColumnInfo(name = "comments_count")
    @Expose
    @SerializedName("comments_count")
    var commentsCount: Int = 0

    @ColumnInfo(name = "thumbs")
    @Expose
    @SerializedName("thumbs")
    var thumbs: List<DoubanMomentNewsThumbs>? = null

    @ColumnInfo(name = "created_time")
    @Expose
    @SerializedName("created_time")
    var createdTime: String? = null

    @ColumnInfo(name = "title")
    @Expose
    @SerializedName("title")
    var title: String? = null

    @ColumnInfo(name = "share_pic_url")
    @Expose
    @SerializedName("share_pic_url")
    var sharePicUrl: String? = null

    @ColumnInfo(name = "type")
    @Expose
    @SerializedName("type")
    var type: String? = null

    @ColumnInfo(name = "id")
    @PrimaryKey
    @Expose
    @SerializedName("id")
    var id: Int = 0

    @ColumnInfo(name = "favorite")
    @Expose
    var isFavorite: Boolean = false

    @ColumnInfo(name = "timestamp")
    @Expose
    var timestamp: Long = 0

    fun setEditorChoice(editorChoice: Boolean) {
        this.is_editor_choice = editorChoice
    }

    fun setLiked(liked: Boolean) {
        this.is_liked = liked
    }

    fun isEditorChoice(): Boolean {
        return is_editor_choice
    }

    fun isLiked(): Boolean {
        return is_liked
    }
}