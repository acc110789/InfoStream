package com.info.app.net.douban_entity

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/11.
 */


class DoubanMomentNewsAuthor {

    @ColumnInfo(name = "is_followed")
    @Expose
    @SerializedName("is_followed")
    var isFollowed: Boolean = false

    @ColumnInfo(name = "uid")
    @Expose
    @SerializedName("uid")
    var uid: String? = null

    @ColumnInfo(name = "author_url")
    @Expose
    @SerializedName("url")
    var url: String? = null

    @ColumnInfo(name = "author_avatar")
    @Expose
    @SerializedName("avatar")
    var avatar: String? = null

    @ColumnInfo(name = "author_name")
    @Expose
    @SerializedName("name")
    var name: String? = null

    @ColumnInfo(name = "author_is_special_user")
    @Expose
    @SerializedName("is_special_user")
    var isSpecialUser: Boolean = false

    @ColumnInfo(name = "author_n_posts")
    @Expose
    @SerializedName("n_posts")
    var nPosts: Int = 0

    @ColumnInfo(name = "author_alt")
    @Expose
    @SerializedName("alt")
    var alt: String? = null

    @ColumnInfo(name = "author_large_avatar")
    @Expose
    @SerializedName("large_avatar")
    var largeAvatar: String? = null

    @ColumnInfo(name = "author_id")
    @Expose
    @SerializedName("id")
    var id: String? = null

    @ColumnInfo(name = "author_is_auth_author")
    @Expose
    @SerializedName("is_auth_author")
    var isAuthAuthor: Boolean = false

    fun getnPosts(): Int {
        return nPosts
    }

    fun setnPosts(nPosts: Int) {
        this.nPosts = nPosts
    }
}