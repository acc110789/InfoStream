package com.info.app.net.guokr_entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by zhangxiaolong on 18/3/11.
 */


class GuokrHandpickNewsAuthor {

    @ColumnInfo(name = "author_ukey")
    @Expose
    @SerializedName("ukey")
    var ukey: String? = null

    @ColumnInfo(name = "author_is_title_authorized")
    @Expose
    @SerializedName("is_title_authorized")
    var isTitleAuthorized: String? = null

    @ColumnInfo(name = "author_nickname")
    @Expose
    @SerializedName("nickname")
    var nickname: String? = null

    @ColumnInfo(name = "author_master_category")
    @Expose
    @SerializedName("master_category")
    var masterCategory: String? = null

    @ColumnInfo(name = "author_amended_reliability")
    @Expose
    @SerializedName("amended_reliability")
    var amendedReliability: String? = null

    @ColumnInfo(name = "author_is_exists")
    @Expose
    @SerializedName("is_exists")
    var isExists: Boolean = false

    @ColumnInfo(name = "author_title")
    @Expose
    @SerializedName("title")
    var title: String? = null

    @ColumnInfo(name = "author_url")
    @Expose
    @SerializedName("url")
    var url: String? = null

    @ColumnInfo(name = "author_gender")
    @Expose
    @SerializedName("gender")
    var gender: String? = null

    @ColumnInfo(name = "author_followers_count")
    @Expose
    @SerializedName("followers_count")
    var followersCount: Int = 0

    @Embedded
    @Expose
    @SerializedName("avatar")
    var avatar: GuokrHandpickNewsAvatar? = null

    @ColumnInfo(name = "author_resource_url")
    @Expose
    @SerializedName("resource_url")
    var resourceUrl: String? = null

}